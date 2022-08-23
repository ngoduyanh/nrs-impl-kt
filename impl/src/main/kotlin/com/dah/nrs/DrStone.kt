package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.DrStone() {
    Entry {
        id = "F-VGMDB-9159"
        title = "Dr. Stone"
        // bac si da

        Entry {
            id = "A-MAL-38691"    // generated
            idMAL = 38691
            idAniList = 105333
            idKitsu = 42080
            idAniDB = 14491
            title = "Dr. Stone"
            bestGirl = "Yuzuriha Ogawa"
            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.3, 0.75)
            // "plot is good"
            AEI(2.5, Emotion.AP)
            FeatureMusic("M-VGMDB-AL-87927-1")
        }

        Contains("M-VGMDB-AL-87927-1")

        Entry {
            id = "A-MAL-40852"    // generated
            idMAL = 40852
            idAniList = 113936
            idKitsu = 42867
            idAniDB = 15305
            title = "Dr. Stone: Stone Wars"
            bestGirl = "Yuzuriha Ogawa"
            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.3, 0.75)
            AEI(1.0, Emotion.AP)
        }

        Entry {
            id = "A-MAL-50612"    // generated
            idMAL = 50612
            idAniList = 142876
            idKitsu = 45615
            idAniDB = 16164
            title = "Dr. Stone: Ryuusui"
            bestGirl = "Yuzuriha Ogawa"
            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.3, 0.75)
            NEI(6.0, Emotion.AP)
        }
    }

    Entry {
        id = "M-VGMDB-AL-87927"
        title = "Good Morning World!"

        Visual(VisualKind.AlbumArt, 0.15, 0.2)

        SubIDEntry("1") {
            Music(2.5)
            OsuSong(personal = 1.0)
        }
    }
}