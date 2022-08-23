package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

// 'That summer I first learned why “Love is War”'
fun DSLScope.Kaguya() {
    // i thought this was blacklisted, but it seems that was not the case
    Entry {
        id = "F-VGMDB-7021"
        title = "Kaguya-sama wa Kokurasetai ~Tensaitachi no Ren'ai Zunousen~"
        // kaguyashit my behated

        Entry {
            id = "M-VGMDB-AL-83397"
            title = "Chikatto Chika Chika♡"
            Visual(VisualKind.AlbumArt, 0.5, 0.4)

            SubIDEntry("1") {
                Music(2.5)
                OsuSong(personal = 5.0, community = 4.0)
            }
        }

        Entry {
            id = "M-VGMDB-AL-116977"
            title = "KAGUYA ♡ ULTRA BEST"

            Visual(VisualKind.AlbumArt, 0.5, 0.4)

            SubIDEntry("11", "Arigatou.") {
                // the grass-touching experience
                Music(3.5)
            }
        }

        // but I got tired of the constant spamming of redditors
        // (more like jealous with its popularity, so it got hated)
        // also the ideology in this anime is kinda fucked.
        // and it's also kinda boring too
        // and now 6.xx became the meta, rst + sb69 top 2, le boat analogy lol
        AEI(-5.0, Emotion.AU) {
            contributors["A-MAL-37999"] = 0.25
            contributors["A-MAL-40591"] = 0.5
            contributors["A-MAL-43608"] = 0.25
        }

        Entry {
            id = "A-MAL-37999"    // generated
            idMAL = 37999
            idAniList = 101921
            idKitsu = 41373
            idAniDB = 14111
            title = "Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen"
            bestGirl = "Hayasaka Ai"
            // https://en.wikipedia.org/wiki/Yumiri_Hanamori#:~:text=On%20November%201%2C%202019%2C%20it%20was%20announced%20that%20Hanamori%20would%20be%20%22graduating%22%20from%20Re%3AStage!%20due%20to%20a%20knee%20injury.

            Progress(Boredom.Dropped, 9)
            Visual(VisualKind.Animated, 0.5, 0.4)
            FeatureMusic("M-VGMDB-AL-83397-1")
        }

        // these are ranked just to help to carry the AEI for s1
        // also the funny rina hidaka girl too lmfao
        Entry {
            id = "A-MAL-40591"    // generated
            idMAL = 40591
            idAniList = 112641
            idKitsu = 42632
            idAniDB = 15207
            title = "Kaguya-sama wa Kokurasetai? Tensai-tachi no Renai Zunousen"
            bestGirl = "Kobachi Osaragi" // aka the mf who 'first learned why “Love is War”'
            // tatoe asu de sekai ga owattemo
            // koukai shinai yo kimi ni deaeta koto

            Visual(VisualKind.Animated, 0.5, 0.4)
            Progress(Boredom.Unwatched)
        }

        Entry {
            id = "A-MAL-43608"    // generated
            idMAL = 43608
            idAniList = 125367
            idKitsu = 43691
            idAniDB = 15806
            title = "Kaguya-sama wa Kokurasetai: Ultra Romantic"
            // imagine almost every anime losing to this shit lol y'all sucks (the MAL top 2 thing)
            // (rst still better copium)

            bestGirl = "Kobachi Osaragi" // aka the mf who 'heard somewhere that “Love strikes so suddenly”'
            // It happened so suddenly, but from now on
            // I want you to quietly listen to what I’ll say
            // Just for now I’ll say “goodbye” to my cowardly heart

            Progress(Boredom.Unwatched)
            Visual(VisualKind.Animated, 0.5, 0.4)
        }
    }
}