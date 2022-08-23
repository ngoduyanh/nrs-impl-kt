package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.ZombielandSaga() {
    Entry {
        id = "F-VGMDB-9825"
        title = "ZOMBIE LAND SAGA"
        Entry {
            id = "A-MAL-37976"    // generated
            idMAL = 37976
            idAniList = 103871
            idKitsu = 41459
            idAniDB = 14198
            title = "Zombieland Saga"
            bestGirl = "Minamoto Sakura"

            // kinda funny
            AEI(5.0, Emotion.AP)
            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.4, 0.5)
        }

        Entry {
            id = "A-MAL-40174"    // generated
            idMAL = 40174
            idAniList = 110733
            idKitsu = 42467
            idAniDB = 15028
            title = "Zombieland Saga: Revenge"
            bestGirl = "Minamoto Sakura"

            // the zombieland saga incident
            Progress(Boredom.Dropped, 7)
            Visual(VisualKind.Animated, 0.4, 0.5)
            KilledBy("V-VNDB-12849", 0.3, 0.2)
            FeatureMusic("M-VGMDB-AL-109368")
        }

        Entry {
            id = "M-VGMDB-AL-109368"
            title = "Yume wo Te ni, Modoreru Basho mo Nai Hibi wo/Kaze no Tsuyoi Hi wa Kirai ka?"

            Visual(VisualKind.AlbumArt, 0.4, 0.5)
            SubIDEntry("1", "Yume wo Te ni, Modoreru Basho mo Nai Hibi wo") {
                Music(5.5)
            }
        }
    }
}