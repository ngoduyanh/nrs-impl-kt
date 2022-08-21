@file:Suppress("unused", "FunctionName")

package com.dah.nrs.exts

import com.dah.nrs.core.*
import com.dah.nrs.dsl.*
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.time.LocalDate
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.tanh
import kotlin.math.withSign

class DAH_standards(builder: NRSContextBuilder) : Extension(builder) {
    init {
        dependencies.addAll(
            listOf(
                DAH_factors::class.simpleName!!
            )
        )
    }
}

data class Level(val index: Int, val name: String, val value: Double)

val Boredom.Completed
    get() = Level(0, "Completed", 1.0)
val Boredom.CompletedWithNoticeableBoredom
    get() = Level(1, "Completed with noticeable boredom", 0.5)
val Boredom.Dropped
    get() = Level(2, "Dropped", -1.0)
val Boredom.Unwatched
    get() = Level(3, "Unwatched", 0.0)
val Boredom.Watching
    get() = Level(4, "Watching", 0.75)
val Boredom.TempOnHold
    get() = Level(5, "Temporarily On-Hold", -0.5)

fun Boredom.PartiallyDropped(value: Double) = Level(6, "Partially dropped", value)

fun AcceptImpact.Impact(block: DSLImpact.() -> Unit) {
    acceptImpact(DSLImpact(context).also(block))
}

fun AcceptRelation.Relation(block: DSLRelation.() -> Unit) {
    acceptRelation(DSLRelation(context).also(block))
}

// we calculate the inverse combine of emotion contributing factors
// basically this ensures that the PADS impact score vector will have
// the following property:
// combine(PADSImpactScoreVector, Emotion.weight) = 1.0
// multiply this vector by `x` to change 1.0 to x in the above property
private fun emotionVector(context: NRSContext, vararg emotions: Pair<Emotion.Factor, Double>): ScoreVector {
    if (emotions.isEmpty()) {
        error("Empty list of emotions")
    }
    val contributingFactors = emotions.map { it.second }
    val combinedFactor = combine(contributingFactors, Emotion.weight)
    val baseScore = 1.0 / combinedFactor

    return context.vector {
        emotions.forEach { (factor, weight) -> set(factor, baseScore * weight) }
    }
}

private fun DSLImpact.emotion(baseScore: Double, vararg emotions: Pair<Emotion.Factor, Double>) {
    score = emotionVector(context, *emotions) * baseScore
    meta("emotions", buildJsonObject {
        emotions.forEach { (factor, weight) -> put(factor.name, weight) }
    })
}

fun AcceptImpact.Cry(vararg emotions: Pair<Emotion.Factor, Double>, block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Cry"
        emotion(4.0, *emotions)
        meta("type", "cry")
        block()
    }
}

fun AcceptImpact.PADS(length: Int, vararg emotions: Pair<Emotion.Factor, Double>, block: DSLImpact.() -> Unit = {}) {
    val padsScore = mapClampThrow(length.toDouble().coerceAtMost(5.0), 1.0..5.0, 3.0..5.0) {
        "PADS too short"
    }

    Impact {
        description = "PADS"
        emotion(padsScore, *emotions)
        meta("type", "pads")
        meta("pads_length", length)
        meta("pads_score", padsScore)
        block()
    }
}

fun AcceptImpact.AEI(score: Double, vararg emotions: Pair<Emotion.Factor, Double>, block: DSLImpact.() -> Unit = {}) {
    val mappedScore = mapClampThrow(abs(score), 0.0..10.0, 2.0..3.0) {
        "$score not in range 0..10"
    }.withSign(score)

    Impact {
        description = "AEI"
        emotion(mappedScore, *emotions)
        meta("type", "aei")
        meta("input_score", score)
        meta("clamped_score", score)
        block()
    }
}

fun AcceptImpact.NEI(score: Double, vararg emotions: Pair<Emotion.Factor, Double>, block: DSLImpact.() -> Unit = {}) {
    val mappedScore = mapClampThrow(abs(score), 0.0..10.0, 0.0..2.0) {
        "$score not in range 0..10"
    }.withSign(score)

    Impact {
        description = "NEI"
        emotion(mappedScore, *emotions)
        meta("type", "nei")
        meta("input_score", score)
        meta("clamped_score", mappedScore)
        block()
    }
}

fun AcceptImpact.Cry(emotion: Emotion.Factor, block: DSLImpact.() -> Unit = {}) = Cry(emotion to 1.0) { block() }
fun AcceptImpact.PADS(length: Int, emotion: Emotion.Factor, block: DSLImpact.() -> Unit = {}) =
    PADS(length, emotion to 1.0) { block() }

fun AcceptImpact.AEI(score: Double, emotion: Emotion.Factor, block: DSLImpact.() -> Unit = {}) =
    AEI(score, emotion to 1.0) { block() }

fun AcceptImpact.NEI(score: Double, emotion: Emotion.Factor, block: DSLImpact.() -> Unit = {}) =
    NEI(score, emotion to 1.0) { block() }

fun AcceptImpact.Waifu(name: String, vararg periods: Pair<String, String>, block: DSLImpact.() -> Unit = {}) {
    if (periods.isEmpty()) {
        error("Empty list of periods")
    }
    val days = periods.sumOf {
        try {
            val from = LocalDate.parse(it.first)
            val to = LocalDate.parse(it.second)
            val days = to.toEpochDay() - from.toEpochDay()
            if (days < 0) {
                error("Found a waifu period with from > to")
            }
            days
        } catch (e: Exception) {
            e.printStackTrace()
            error("Can't parse waifu periods")
        }
    }

    WaifuUnknownPeriod(name, days.toInt()) {
        meta("periods", buildJsonArray {
            periods.forEach {
                addJsonObject {
                    put("from", it.first)
                    put("to", it.second)
                }
            }
        })
        block()
    }
}

fun AcceptImpact.WaifuUnknownPeriod(name: String, days: Int, block: DSLImpact.() -> Unit = {}) {
    val waifuScore = 1.5 * tanh(days.toDouble() / 60)

    Impact {
        description = "Waifu"
        score = vector {
            set(Emotion.MP, waifuScore)
        }
        meta("type", "waifu")
        meta("name", name)
        meta("length_in_days", days)
        block()
    }
}

fun AcceptImpact.EHI(block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "EHI"
        score = vector {
            set(Emotion.AP, 3.5)
        }
        meta("type", "ehi")
        block()
    }
}

fun AcceptImpact.EPI(plotScore: Double, block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "EPI"
        score = vector {
            set(Emotion.AP, mapClampThrow(plotScore, 0.0..10.0, 3.5..4.5) {
                "$score not in range 0..10"
            })
        }
        meta("type", "epi")
        meta("input_score", plotScore)
        block()
    }
}

fun AcceptImpact.Jumpscare(block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Jumpscare"
        score = vector {
            set(Emotion.MU, 1.0)
        }
        meta("type", "jumpscare")
        block()
    }
}

// horror
fun AcceptImpact.SleeplessNight(block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Sleepless Night (Horror)"
        score = vector {
            set(Emotion.MU, 4.0)
        }
        meta("type", "sleepless_night_horror")
        block()
    }
}

fun AcceptImpact.Politics(block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Inspired political discovery"
        score = vector {
            set(Additional, 0.75)
        }
        meta("type", "politics_inspiration")
        block()
    }
}

fun AcceptImpact.InterestField(newField: Boolean, block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Made me interested in a field"
        score = vector {
            set(Additional, if (newField) 2.0 else 1.0)
        }
        meta("type", "field_interest_gateopen")
        meta("new_field", newField)
        block()
    }
}

fun DSLEntry.Progress(boredomLevel: Level, episode: Int? = null) {
    Impact {
        description = "Boredom: ${boredomLevel.name}"
        score = vector {
            set(Boredom, boredomLevel.value)
        }
        meta("type", "boredom")
        meta("boredom_level", boredomLevel.index)
        meta("boredom_level_name", boredomLevel.name)
    }
    meta("DAH_entry_progress", DSLMetaImpl().apply {
        meta("status", boredomLevel.name)
        episode?.let { meta("episode", it) }
    })
}

fun AcceptImpact.Meme(strength: Double, duration: Int, block: DSLImpact.() -> Unit = {}) {
    if (strength !in 0.0..2.0) {
        error("$strength not in range 0..2")
    }

    val durationValue = (duration.toDouble() / 120).pow(0.25)

    Impact {
        description = "Meme"
        score = vector {
            set(Emotion.AP, strength * durationValue * 4.0)
        }
        meta("type", "meme")
        meta("strength", strength)
        meta("meme_duration", duration)
        meta("meme_duration_value", durationValue)
        block()
    }
}

fun AcceptImpact.AdditionalImpact(description: String, value: Double, block: DSLImpact.() -> Unit = {}) {
    Impact {
        this.description = description
        score = vector {
            set(Additional, value)
        }
        meta("type", "additional")
        block()
    }
}

// temporary standard: 5.0 is Re:Rays' level
fun AcceptImpact.Music(musicScore: Double, block: DSLImpact.() -> Unit = {}) {
    Impact {
        description = "Music"
        score = vector {
            set(Art.Music, mapClampThrow(musicScore, 0.0..10.0, 0.0..3.0) {
                "$musicScore not in range 0..10"
            })
        }
        meta("type", "DAH_nonstandard_music")
        meta("input_score", musicScore)
        block()
    }
}

private fun calcVisualScore(b: Double, u: Double): Double {
    // let the visual score function be f(u, b)
    // (u: uniqueness, b: base)
    // we may let f(u, b) = g(b) + h(u, b)
    // g: represents the actual visual
    // h: buff unique anime

    // let p(b) = f(1, b)/f(0, b)
    // then we have h(1, b) = p(b)h(0, b) + g(b)(p(b) - 1)
    // assume h is linear (to u), we have:
    // h(u, b) = (1 + u(p(b)-1))h(0, b) + ug(b)(p(b)-1)
    // then f(u, b) = g(b) + (1 + u(p(b)-1))h(0, b) + ug(b)(p(b)-1)
    //              = (1 +u(p(b)-1))(h(0, b) + g(b))
    // let k(b) = g(b) + h(0, b), we can rewrite f as
    // f(u, b) = (1 + u(p(b) - 1))k(b)
    // since f(1, b) > f(0, b), we need p(b) > 1 for every b
    // with that in mind, we can choose p and k
    // p(b) = s * max(b^t, 1)
    // k(b) = b^r
    // then we fine-tune r, s, and t

    // (another version of p can be p(b) = max(s * b^t, 1))

    // after finding f, by normalizing, we get the normalized f function:
    // f(u, b) = (1 + u(p(b) - 1))k(b) / s

    // fine-tuned values
    val r = 2.04
    val s = 1.133
    val t = -1.4

    val p = s * b.pow(t).coerceAtLeast(1.0)
    val k = b.pow(r)
//    return (1 + u * (p - 1)) * k / s

    return b * (2 + u) / 3
}


enum class VisualKind(val baseScore: Double) {
    Animated(3.0),

    // A-MAL-38009: https://animixplay.to/v1/restage-dream-days
    RPG3DGame(2.0),

    // G-VGMDB-1880: https://store.steampowered.com/app/1152310/Atelier_Escha__Logy_Alchemists_of_the_Dusk_Sky_DX/
    AnimatedShort(1.0),

    // A-MAL-34240: https://www.youtube.com/watch?v=fzQ6gRAEoy0
    AnimatedGachaCardArt(1.0),

    // (unranked): https://twitter.com/lapi_staff/status/1555750517887975425
    AnimatedMV(1.0),

    // M-VGMDB-AL-100087-1: https://www.youtube.com/watch?v=IqdpYyaLnNc
    SemiAnimatedMV(0.8),

    // M-VGMDB-AL-116297-1: https://www.youtube.com/watch?v=vyaGNvuVDuM
    GachaCardArt(0.8),

    // GF-VGMDB-7059: https://lldetail.ml/Restage/card/index_secret.php
    LightNovel(0.8),

    // L-MAL-89357 (unranked): https://danbooru.donmai.us/posts/2905913
    Manga(0.8),

    // L-MAL-126146 (unranked): https://en.wikipedia.org/wiki/Oshi_no_Ko#/media/File:Oshi_no_Ko_Volume_1.jpg
    VisualNovel(0.5),

    // V-VNDB-12849: https://danbooru.donmai.us/posts/2234064
    StaticMV(0.25),

    // M-VGMDB-AL-121168 (not canon): https://www.youtube.com/watch?v=hj_4YAVmmuI
    AlbumArt(0.25),
    // M-VGMDB-AL-121168 (canon): https://medium-media.vgm.io/albums/86/121168/121168-de8dcf1b4ceb.jpg
}

fun AcceptImpact.Visual(kind: VisualKind, base: Double, uniqueness: Double, block: DSLImpact.() -> Unit = {}) {
    if (base !in 0.0..1.0 || uniqueness !in 0.0..1.0) {
        error("invalid base/uniqueness")
    }

    Impact {
        description = "Generic visual"
        score = vector {
            set(Art.Visual, calcVisualScore(base, uniqueness) * kind.baseScore)
        }
        meta("type", "DAH_nonstandard_generic_visual")
        meta("base", base)
        meta("uniqueness", uniqueness)
        meta("kind", kind.toString())
        block()
    }
}

fun MusicContainFactor(rate: Double) = rate.pow(1.0 / ChemistryBuffFactor)
fun MusicContainFactor(numEntries: Int, numContributeEntries: Int) =
    MusicContainFactor(numEntries.toDouble() / numContributeEntries)

fun AcceptImpact.OsuSong(personal: Double = 0.0, community: Double = 0.0) {
    val personalFactor = mapClampThrow(personal, 0.0..10.0, 0.0..0.5) {
        "$personal not in range 0..10"
    }

    val communityFactor = mapClampThrow(community, 0.0..10.0, 0.0..0.2) {
        "$community not in range 0..10"
    }

    Impact {
        description = "osu! song"
        score = vector {
            set(Emotion.AP, personalFactor + communityFactor)
        }
        meta("type", "DAH_nonstandard_osu_song")
        meta("personal_input", personal)
        meta("community_input", community)
        meta("personal_factor", personalFactor)
        meta("community_factor", communityFactor)
    }
}

fun AcceptRelation.Remix(id: String, block: DSLRelation.() -> Unit = {}) {
    Relation {
        references[id] = vector(0.2).toDiagonalMatrix()
        block()
    }
}

fun AcceptRelation.FeatureMusic(id: String, block: DSLRelation.() -> Unit = {}) {
    Relation {
        references[id] = vector {
            set(Art.Music, 0.2)
        }.toDiagonalMatrix()
        block()
    }
}

fun AcceptRelation.KilledBy(id: String, potential: Double, effect: Double, block: DSLRelation.() -> Unit = {}) {
    Relation {
        references[id] = (vector {
            set(Emotion.AP, 0.2)
            set(Emotion.AU, 0.1)
            set(Emotion.CP, 0.05)
            set(Emotion.CU, 0.05)
            set(Emotion.MP, 0.2)
            set(Emotion.MU, 0.1)

            set(Art.Visual, 0.0)
            set(Art.Language, 0.1)
            set(Art.Music, 0.1)

            set(Boredom, 0.1)
            set(Additional, 0.0)
        } * potential * effect * 2.0).toDiagonalMatrix()
        block()
    }
}

@Suppress("unused")
fun AcceptRelation.GateOpen(id: String, block: DSLRelation.() -> Unit = {}) {
    // TODO: a good gateopen impl
}

// 4-4-2 standard (not yoinked from football btw)
const val MusicVocalContainFactor = 0.4
const val MusicInstContainFactor = 0.4
const val MusicImageContainFactor = 0.2
const val MusicVocalImageContainFactor = MusicVocalContainFactor + MusicImageContainFactor
const val MusicMainArtistFactor = 0.6
const val MusicFeatArtistFactor = 0.4

fun mapClampThrow(
    input: Double,
    inputRange: ClosedRange<Double>,
    outputRange: ClosedRange<Double>,
    block: () -> String,
): Double {
    if (input !in inputRange) {
        error(block())
    }

    return map(input, inputRange, outputRange)
}
