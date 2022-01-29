@file:UseSerializers(
    ScoreNumSerializer::class, FactorScoreSerializer::class,
    ImpactSubscoreSerializer::class, ImpactScoreSubscoresSerializer::class
)
@file:Suppress("unused",         // I'll use them stfu
    "MemberVisibilityCanBePrivate",     // idc
    "FunctionName",                     // It's my DSL, idc about conventions
    "SpellCheckingInspection"           // IDEA can't understand nihongo
)
@file:OptIn(ExperimentalSerializationApi::class)

package com.dah.nrs

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.math.BigDecimal
import java.nio.file.StandardOpenOption
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.io.path.Path
import kotlin.io.path.outputStream
import kotlin.math.absoluteValue
import kotlin.math.tanh
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias ScoreNum = BigDecimal

private fun Iterable<ScoreNum>.sum() = reduceOrNull { a, b -> a + b } ?: num(0)

open class OneWayToStringSerializer<T> : KSerializer<T> {
    override val descriptor = PrimitiveSerialDescriptor(javaClass.canonicalName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): T {
        error("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(value.toString())
    }
}

object ScoreNumSerializer : OneWayToStringSerializer<ScoreNum>() {
    override fun deserialize(decoder: Decoder): ScoreNum {
        return decoder.decodeString().toBigDecimal()
    }
}

object FactorScoreSerializer : OneWayToStringSerializer<FactorScore>()
object ImpactSubscoreSerializer : OneWayToStringSerializer<ImpactSubscore>()
object ImpactScoreSubscoresSerializer : OneWayToStringSerializer<ImpactScoreSubscores>()

fun num(v: Double): ScoreNum {
    return v.toBigDecimal()
}

fun num(v: Long): ScoreNum {
    return v.toBigDecimal()
}

@Serializable
data class Entry(
    val id: String,
    val title: String,
    val type: EntryType,
    val status: EntryStatus,
    val seasonal: Boolean,
    val score: Score,
    val contains: List<String>,
    val artists: List<String>
)

@Serializable
enum class EntryType {
    Anime,
    Manga,
    LightNovel,
    VisualNovel,
    Franchise,
    Music,
    Other
}

@Serializable
enum class EntryStatus {
    Completed,
    Watching,
    Other
}

@Serializable
data class Score(
    val impact: ImpactScore,
    val relation: RelationScore,
    val overall: ScoreNum
)

class ImpactScoreSubscores(base: Map<Subscore, SubscoreValue>) : Map<Subscore, SubscoreValue> by base {
    override fun toString(): String {
        return Subscores.joinToString(";") {
            val values = this[it] ?: SubscoreValue(FactorScores.associateWith { num(0) }, num(0))
            "${values.value}:" + it.factors.map { values.factorScores[it] ?: num(0) }.joinToString(":")
        }
    }
}

@Serializable
data class ImpactScore(
    val impacts: List<Impact>,
    val subscores: ImpactScoreSubscores,
    val value: ScoreNum = subscores.values.map { it.value }.sum()
)

@Serializable
data class SubscoreValue(
    val factorScores: Map<FactorScore, ScoreNum>,
    val value: ScoreNum = combine(factorScores.values, factorScores.keys.first().parent.weight)
)

class ImpactSubscore(base: Map<Subscore, Map<FactorScore, ScoreNum>>) : Map<Subscore, Map<FactorScore, ScoreNum>> by base {
    override fun toString(): String {
        return FactorScores.map { this[it.parent]?.get(it) }
            .joinToString(":")
    }
}

@Serializable
class Impact(
    val description: String,
    val subscores: ImpactSubscore,
    val meta: Map<String, String>,
    val from: MutableList<String> = arrayListOf()
) {
    fun from(vararg id: String) = this.also { from.addAll(id) }
}

@Serializable
data class RelationScore(
    val relations: List<Relation>,
    val value: ScoreNum = relations.map { it.value }.sum()
)

@Serializable
class Relation(
    val id: String,
    val description: String,
    val weight: ScoreNum,
    val from: List<String>,
    val value: ScoreNum
)

class UnscoredRelation(
    val id: String,
    val description: String,
    val weight: ScoreNum,
    val from: MutableList<String> = arrayListOf()
) {
    fun score(score: ScoreNum) = Relation(id, description, weight, from, score)

    fun from(vararg id: String) = this.also { from.addAll(id) }
}

sealed class Subscore(val name: String, @Transient val weight: ScoreNum = num(0.0)) {
    val factors = arrayListOf<FactorScore>()
    constructor(name: String, weight: Double) : this(name, num(weight))
    fun factor(name: String = this.name, weight: Double = this.weight.toDouble())
        = FactorScore(this, this.name + "-" + name, num(weight)).also { factors.add(it) }
    override fun toString() = name
}

class FactorScore constructor(val parent: Subscore, val name: String, val weight: ScoreNum) {
    override fun toString() = name
}

object Emotion : Subscore("Emotion", 0.6) {
    val ActivatedUnpleasant = factor("ActivatedUnpleasant", 0.3)
    val ActivatedPleasant = factor("ActivatedPleasant", 0.4)
    val ModerateUnpleasant = factor("ModerateUnpleasant", 0.35)
    val ModeratePleasant = factor("ModeratePleasant", 0.35)
    val CalmingUnpleasant = factor("CalmingUnpleasant", 0.4)
    val CalmingPleasant = factor("CalmingPleasant", 0.5)

    val AU = ActivatedUnpleasant
    val AP = ActivatedPleasant
    val MU = ModerateUnpleasant
    val MP = ModeratePleasant
    val CU = CalmingUnpleasant
    val CP = CalmingPleasant
}

object Art : Subscore("Art", 0.4) {
    val Language = factor("Language", 0.3)
    val Illustration = factor("Illustration", 0.3)
    val Music = factor("Music", 0.4)

    val L = Language
    val I = Illustration
    val M = Music
}

object Information : Subscore("Information", 0.5) {
    val Politics = factor("Politics", 0.7)
    val GeneralInfo = factor("GeneralInfo", 0.5)
}

typealias BoredomLevel = Pair<String, ScoreNum>

object Boredom : Subscore("Boredom", 0.0) {
    val BoredomFactor = factor()

    fun level(name: String, value: Double): BoredomLevel = name to num(value)
    val Completed = level("Completed", 1.0)
    val CompletedWithNoticeableBoredom = level("Completed with noticeable boredom", 0.5)
    val Dropped = level("Dropped", -1.0)
    val Unwatched = level("Unwatched", 0.0)
    val Watching = level("Watching", 0.75)
    val TempOnHold = level("Temporarily On-Hold", -0.5)
    val PartiallyDropped = { value: Double -> level("Partially dropped", value) }
}

object Fandom : Subscore("Fandom", 1.0) {
    val FandomFactor = factor()
}

object Additional : Subscore("Additional", 1.0) {
    val AdditionalFactor = factor()
}

typealias MemeLevel = Pair<String, ScoreNum>
object Meme {
    fun level(name: String, value: Double): MemeLevel = name to num(value)
    val MLessThanADay = level("Less than a day", 0.0)
    val M1_3Days = level("1-3 Days", 0.1)
    val M4_7Days = level("4-7 Days", 0.3)
    val M1_2Weeks = level("1-2 Weeks", 0.5)
    val M2_3Weeks = level("2-3 Weeks", 0.6)
    val M3Weeks_1Month = level("3 Weeks - 1 Month", 0.7)
    val M1_2Months = level("1-2 Months", 0.8)
    val M2_3Months = level("2-3 Months", 0.9)
    val MMoreThan3Months = level("More than 3 months", 1.0)
}

val Subscores = listOf(Emotion, Art, Information, Boredom, Fandom, Additional)
val FactorScores = Subscores.flatMap { it.factors }

fun MAL(id: Int) = "A-MAL-$id"

fun VGMDB_Album(id: Int) = "M-VGMDB-AL-$id"

fun VGMDB_Track(id: Int, trackNum: Int) = "${VGMDB_Album(id)}-$trackNum"

fun VGMDB_Artist(id: Int) = "M-VGMDB-AR-$id"

fun VGMDB_Franchise(id: Int) = "F-VGMDB-$id"

open class GenerateBlock {
    val entries = hashMapOf<String, EntryBlock>()

    fun AnimeMangaLightNovel(type: String, title: String,
                             MAL: Int = -1, AL: Int = -1, ADB: Int = -1, KS: Int = -1,
                             block: EntryBlock.() -> Unit = {}) = let {
        if(MAL > 0) {
            Entry("$type-MAL-$MAL", title, block)
        } else if(AL > 0) {
            Entry("$type-AL-$AL", title, block)
        } else if(ADB > 0) {
            Entry("$type-ADB-$ADB", title, block)
        } else if(KS > 0) {
            Entry("$type-KS-$KS", title, block)
        } else {
            error("tf")
        }
    }

    fun Anime(title: String, MAL: Int = -1, AL: Int = -1, ADB: Int = -1, KS: Int = -1, block: EntryBlock.() -> Unit = {}) =
        AnimeMangaLightNovel("A", title, MAL, AL, ADB, KS, block)

    fun Manga(title: String, MAL: Int = -1, AL: Int = -1, ADB: Int = -1, KS: Int = -1, block: EntryBlock.() -> Unit = {}) =
        AnimeMangaLightNovel("L", title, MAL, AL, ADB, KS, block)

    fun LN(title: String, MAL: Int = -1, AL: Int = -1, ADB: Int = -1, KS: Int = -1, block: EntryBlock.() -> Unit = {}) =
        AnimeMangaLightNovel("L", title, MAL, AL, ADB, KS, block)

    fun VN(VNDB: Int, title: String, block: EntryBlock.() -> Unit = {}) = Entry("V-VNDB-$VNDB", title, block)

    fun Track(id: String, title: String, vararg artists: String, block: EntryBlock.() -> Unit = {}) = Entry(id, title) {
        this.artists.addAll(artists)
        block()
    }

    fun Entry(id: String, title: String, block: EntryBlock.() -> Unit = {}): String {
        if(entries.containsKey(id)) {
            error("Duplicate entry: $id")
        }

        val entry = EntryBlock(id, title)
        entries[id] = entry
        entry.block()
        return id
    }

    fun Artist(id: String, title: String, block: EntryBlock.() -> Unit = {}) = Entry(id, title) {
        artist = true
        block()
    }

    fun Album(id: String, title: String, block: EntryBlock.() -> Unit = {}) = Entry(id, title) {
        album = true
        block()
    }

    fun AlbumTrack(albumId: String, trackNum: Int, title: String, vararg artists: String, block: EntryBlock.() -> Unit = {}) {
        Album(albumId, title)
        Track("$albumId-$trackNum", title, *artists) { block() }
    }

    fun Franchise(id: String, title: String, block: EntryBlock.() -> Unit = {}) = Entry(id, title, block)

    fun Game(id: String, title: String, block: EntryBlock.() -> Unit = {}) = Entry(id, title, block)

    open fun Impact(impact: Impact): Impact { return impact }

    open fun Relation(relation: UnscoredRelation): UnscoredRelation { return relation }

    fun ImpactEx(description: String = "", meta: Map<String, String> = mapOf(), vararg scores: Pair<FactorScore, ScoreNum>): Impact {
        val scoreMap = mapOf(*scores)
        val impactScores = ImpactSubscore(Subscores.associateWith { subscore ->
            subscore.factors.associateWith { (scoreMap[it] ?: num(0.0)) }
        })

        return Impact(Impact(description, impactScores, meta))
    }

    fun Impact(description: String = "", factorScore: FactorScore, score: Double, meta: Map<String, String> = mapOf())
            = Impact(description, meta, factorScore to score)

    fun Impact(description: String = "", factorScore: FactorScore, score: ScoreNum, meta: Map<String, String> = mapOf())
            = ImpactEx(description, meta, factorScore to score)

    fun Impact(description: String = "", meta: Map<String, String> = mapOf(), vararg scores: Pair<FactorScore, Double>)
            = ImpactEx(description, meta, *scores.map { it.first to num(it.second) }.toTypedArray())

    fun PADS(duration: Int, emotion: FactorScore) = let {
        if(duration < 1) {
            error("PADS too short")
        }

        if(emotion.parent != Emotion) {
            error("PADS gives only emotion factor scores")
        }

        val meta = meta("padsLength" to duration)
        val clampedDuration = duration.coerceIn(1..5)
        val score = (clampedDuration.toBigDecimal() - num(1)) / num(2) + num(3)

        Impact("PADS", emotion, score, meta)
    }

    // TODO: nerf Cry in specs
    fun Cry(factor: FactorScore) = Impact("Cry", factor, 4.0)

    fun AEI(score: Double, emotion: FactorScore) = let {
        val scale = when(emotion) {
            Emotion.AU -> 0.7
            Emotion.AP, Emotion.MU -> 0.8
            Emotion.MP -> 0.9
            Emotion.CU, Emotion.CP -> 1.0
            else -> error("This emotion/Other factors can't cause AEI")
        }
        if(score !in 2.0 .. 3.0) {
            error("AEI score not in 2..3 range")
        }
        Impact(
            "AEI (Appreciable Emotion Impact)", emotion,
            score * scale, meta("AEIScore" to score)
        )
    }

    fun NEI(score: Double, emotion: FactorScore) = let {
        val scale = when(emotion) {
            Emotion.AU -> 0.6
            Emotion.AP, Emotion.MU -> 0.8
            Emotion.MP -> 0.9
            Emotion.CU, Emotion.CP -> 1.0
            else -> error("This emotion/Other factors can't cause NEI")
        }
        if(score !in 0.0 .. 2.0) {
            error("NEI score not in 0..2 range")
        }
        Impact(
            "NEI (Noticeable Emotion Impact)", emotion,
            score * scale, meta("NEIScore" to score)
        )
    }

    fun Waifu(name: String, vararg periods: Pair<String, String>) = let {
        val days = periods.sumOf {
            try {
                val from = LocalDate.parse(it.first)
                val to = LocalDate.parse(it.second)
                val days = to.toEpochDay() - from.toEpochDay()
                if(days < 0) {
                    error("Found a waifu period with from > to")
                }
                days.absoluteValue
            } catch (e: Exception) {
                e.printStackTrace()
                error("Can't parse waifu periods")
            }
        }

        val score = num(1.5) * num(tanh(days.toDouble()))

        Impact(
            "Waifu", Emotion.MP, score, meta(
                "waifuName" to name,
                "periods" to periods,
                "length" to days
            )
        )
    }

    fun WaifuUnknownPeriod(name: String, days: Int) = let {
        val score = num(1.5) * num(tanh(days.toDouble()))

        Impact(
            "Waifu", Emotion.MP, score, meta(
                "waifuName" to name,
                "length" to days
            )
        )
    }

    fun EHI() = Impact("EHI", Emotion.AP, 3.5)

    fun EPI(score: Double) = let {
        if(score !in 3.5 .. 4.5) {
            error("EPI score out of range")
        }

        Impact("EPI", Emotion.AP, score)
    }

    fun Jumpscare() = Impact("(Successful) Jumpscare", Emotion.MU, 1.0)

    fun SleeplessNight() = Impact("Sleepless Night", Emotion.MU, 4.0)

    fun Info(type: FactorScore, newField: Boolean = false) = let {
        // TODO: nerf info score in the specs
        val impact = when(type) {
            Information.Politics -> "Political Impact" to 0.75
            else -> if(newField) {
                "New Field Inspiration" to 2.0
            } else {
                "Known Field Inspiration" to 1.5
            }
        }

        Impact(impact.first, type, impact.second)
    }

    fun Boredom(level: BoredomLevel) = Impact(level.first, Boredom.BoredomFactor, level.second)

    fun Meme(strength: Double, level: MemeLevel) = Impact(
        "Meme", Emotion.AP, num(strength) * level.second * num(3), meta(
            "memeStrength" to strength,
            "memeLevel" to level.first,
            "memeLevelValue" to level.second
        )
    )

    fun Music(score: Double) = Impact("Music", Art.Music, score)

    fun Relation(id: String, weight: Double, description: String): UnscoredRelation {
        return Relation(UnscoredRelation(id, description, num(weight)))
    }

    fun KilledBy(id: String) = Relation(id, 1e-2, "Killed By")

    fun GateOpen(id: String) = Relation(id, 1e-4, "Gate Open")

    fun Revive(id: String) = Relation(id, 0.5e-4, "Revive")

    fun FeatureMusic(id: String) = Relation(id, 0.1, "Feature Music")

    fun RemixOf(id: String) = Relation(id, 1e-3, "Remix Of")
}

class EntryBlock(val id: String, val title: String) : GenerateBlock() {
    private fun inferEntryTypeFromID(id: String): EntryType {
        return when(id[0]) {
            'A' -> EntryType.Anime
            'L' -> EntryType.LightNovel
            'V' -> EntryType.Franchise
            'F' -> EntryType.Franchise
            'M' -> EntryType.Music
            else -> EntryType.Other
        }
    }

    var type = inferEntryTypeFromID(id)
    var bestGirl: String? = ""
    var status = EntryStatus.Completed
    var seasonal = false

    var artists = hashSetOf<String>()
    var contains = hashSetOf<String>()

    var impacts = hashSetOf<Impact>()
    var relations = hashSetOf<UnscoredRelation>()

    var artist = false
    var album = false

    var impactScore: ImpactScore? = null
    var relationScore: RelationScore? = null
    var overallScore: ScoreNum? = null

    lateinit var scoredRelations: List<Relation>

    fun Artist(vararg artists: String) {
        this.artists.addAll(artists)
    }

    override fun Relation(relation: UnscoredRelation) = relation.from(id).also { relations.add(it) }

    override fun Impact(impact: Impact) = impact.from(id).also { impacts.add(it) }

    fun Include(id: String) {
        contains.add(id)
    }
}

fun combine(arr: Collection<ScoreNum>, weight: ScoreNum): ScoreNum {
    val pos = arr.filter { it > num(0) }
    val negAbs = arr.filter { it < num(0) }.map { it.abs() }
    fun combineUnsigned(arr: List<ScoreNum>, weight: ScoreNum): ScoreNum {
        var result = num(0)
        arr.sorted().forEach {
            result = result * weight + it
        }
        return result
    }
    return combineUnsigned(pos, weight) - combineUnsigned(negAbs, weight)
}

fun GenerateBlock.calcImpact(id: String) {
    val entry = entries[id] ?: return

    if(entry.impactScore != null) {
        return
    }

    entry.impactScore = ImpactScore(
        entry.impacts.toList(),
        ImpactScoreSubscores(Subscores.associateWith { subscore ->
            SubscoreValue(
                subscore.factors.associateWith { factorScore ->
                    combine(
                        entry.impacts.map { it.subscores[subscore]?.get(factorScore) ?: num(0) },
                        factorScore.weight
                    )
                }
            )
        })
    )
}

fun GenerateBlock.calcRelation(id: String) {
    fun calcRelationInternal(id: String, stack: Set<String>): ScoreNum {
        val relatedEntry = entries[id] ?: return num(0)

        if(relatedEntry.impactScore == null) {
            calcImpact(id)
        }

        if(id in stack) {
            return relatedEntry.impactScore!!.value
        }

        if(relatedEntry.overallScore == null) {
            if(relatedEntry.relationScore == null) {
                val newStack = hashSetOf(*stack.toTypedArray())
                newStack.add(id)
                relatedEntry.relationScore = RelationScore(relatedEntry.relations
                    .map { it.score(calcRelationInternal(it.id, newStack) * it.weight) })
            }

            calcOverall(id)
        }

        return relatedEntry.overallScore!!
    }

    calcRelationInternal(id, setOf())
}

fun GenerateBlock.calcOverall(id: String) {
    val entry = entries[id] ?: return

    if(entry.impactScore == null) {
        calcImpact(id)
    }

    if(entry.relationScore == null) {
        calcRelation(id)
    }

    entry.overallScore = entry.impactScore!!.value + entry.relationScore!!.value
}

private val json = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

@Serializable
data class SubscoreMeta(val name: String, val factorScores: List<String>) {
    constructor(subscore: Subscore): this(subscore.name, subscore.factors.map { it.name })
}

@Serializable
data class OutputData(val lastUpdated: String, val subscores: List<SubscoreMeta>, val entries: List<Entry>)

fun generate(test: Boolean = false, block: GenerateBlock.() -> Unit = {}): OutputData {
    val generateBlock = GenerateBlock()
    Global.generateBlock = generateBlock
    generateBlock.block()
    // init all artists
    Artist.artists.filter { !it.property.isInitialized() }
        .forEach { it.value }
    Artist.artists.clear()

    fun preprocess(entry: EntryBlock) {
        val entries = generateBlock.entries
        val id = entry.id
        if(entry.album) {
            entries.keys
                .filter { it.startsWith(id) && it != id }
                .forEach { entry.contains.add(it) }
        } else if(entry.artist) {
            entries.values
                .filter { it.artists.contains(id) }
                .forEach { entry.contains.add(it.id) }
        }

        entry.contains.mapNotNull { generateBlock.entries[it] }
            .onEach { preprocess(it) }
            .map { it.contains }
            .forEach { entry.contains.addAll(it) }

        entry.impacts.addAll(entry.contains
            .mapNotNull { entries[it] }
            .onEach { preprocess(it) }
            .flatMap { it.impacts }
            .toSet())

        entry.relations.addAll(entry.contains
            .mapNotNull { entries[it] }
            .onEach { preprocess(it) }
            .flatMap { it.relations }
            .filter { it.id !in entry.contains } // not allowing self relations
            .toSet())
    }

    generateBlock.entries.values.forEach {
        preprocess(it)
    }

    val data = generateBlock.entries.values.map {
        inspectEntry(it)
        generateBlock.calcOverall(it.id)
        Entry(
            it.id,
            it.title,
            it.type,
            it.status,
            it.seasonal,
            Score(
                it.impactScore!!,
                it.relationScore!!,
                it.overallScore!!
            ),
            it.contains.toList(),
            it.artists.toList()
        )
    }

    val outputData = OutputData(LocalDateTime.now().toString(), Subscores.map { SubscoreMeta(it) }, data)

    if(!test) {
        Path("../nrs.json").outputStream(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING).use {
            it.write(json.encodeToString(outputData).toByteArray())
        }
    }

    return outputData
}

fun inspectEntry(entry: EntryBlock) {
    if(entry.type in arrayOf(EntryType.Anime, EntryType.LightNovel, EntryType.VisualNovel, EntryType.Manga)) {
        // completable entries
        // should include a boredom impact
        val hasBoredomImpact = entry.impacts.any {
            val boredom = it.subscores[Boredom]?.get(Boredom.BoredomFactor)
            boredom != null && (boredom.signum() != 0 || it.description.lowercase() == "boredom")
        }
        if(!hasBoredomImpact) {
            println("Entry ${entry.id} (title: '${entry.title}') doesn't have a boredom impact")
        }
    }
}

fun meta(vararg entries: Pair<String, Any>) = mapOf(*entries.map { it.first to it.second.toString() }.toTypedArray())

object Global {
    lateinit var generateBlock: GenerateBlock
}

class Artist(id: String, name: String, block: EntryBlock.() -> Unit = {}) : ReadOnlyProperty<Any?, String> {
    companion object {
        val artists = arrayListOf<Artist>()
    }

    init {
        artists.add(this)
    }

    val property = lazy { Global.generateBlock.Artist(id, name, block) }
    val value by property

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }
}

fun today() = LocalDate.now().toString()