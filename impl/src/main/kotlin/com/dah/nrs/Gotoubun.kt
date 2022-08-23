package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.Gotoubun() {
    Entry {
        id = "F-VGMDB-9342"
        title = "Gotoubun no Hanayome"

        Entry {
            id = "A-MAL-38101"    // generated
            idMAL = 38101
            idAniList = 103572
            idKitsu = 41966
            idAniDB = 14247
            title = "Gotoubun no Hanayome"

            // i miss yuyuyu's idke memes
            bestGirl = "Nakano Itsuki"

            Progress(Boredom.Completed)
            // fuwa fuwa doki doki
            AEI(5.0, Emotion.AP)
            Visual(VisualKind.Animated, 0.5, 0.5)
            FeatureMusic("M-VGMDB-AL-82284")
        }

        Entry {
            id = "A-MAL-39783"    // generated
            idMAL = 39783
            idAniList = 109261
            idKitsu = 42324
            idAniDB = 14903
            title = "5-toubun no Hanayome ∬"

            // lmfao "muh yuyuyu idc"
            bestGirl = "Nakano Itsuki"

            Progress(Boredom.Completed)
            // at first I doubted that S2 will be as good as S1
            // but it actually is
            AEI(2.5, Emotion.AP)
            Visual(VisualKind.Animated, 0.5, 0.5)
        }

        Entry {
            id = "M-VGMDB-AL-82284"
            title = "Gotoubun no Kimochi"
            Visual(VisualKind.AlbumArt, 0.5, 0.5)

            SubIDEntry("1") {
                Music(3.0)
            }
        }
    }
}
