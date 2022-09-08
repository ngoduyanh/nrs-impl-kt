package com.dah.nrs

import com.dah.nrs.dsl.DSLScope
import com.dah.nrs.dsl.Entry
import com.dah.nrs.dsl.SubIDEntry
import com.dah.nrs.exts.*

fun DSLScope.SelePro() {
    Entry {
        id = "F-VGMDB-9703"
        title = "SELECTION PROJECT"

        Entry {
            id = "A-MAL-44275"
            bestGirl = "Koizumi Uta"

            Cry(Emotion.CU)
            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.5, 0.3)

            KilledBy("F-VGMDB-7059", potential = 0.25, effect = 0.5)
            KilledBy("F-VGMDB-3245", potential = 0.25, effect = 0.8)
        }

        Entry {
            id = "M-50"
            title = "9-tie"

            Contains(MusicVocalImageContainFactor) {
                Contains("M-VGMDB-AL-114102")
            }
        }

        Entry {
            id = "M-VGMDB-AL-114102"

            Visual(VisualKind.AlbumArt, 0.5, 0.3)
            SubIDEntry("1") {
                Music(5.0)
            }
        }
    }
}
