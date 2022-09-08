package com.dah.nrs

import com.dah.nrs.dsl.DSLScope
import com.dah.nrs.dsl.Entry
import com.dah.nrs.dsl.SubIDEntry
import com.dah.nrs.exts.*

fun DSLScope.Sakurasou() {
    Entry {
        id = "F-VGMDB-2090"
        title = "Sakurasou no Pet na Kanojo"

        Entry {
            id = "A-MAL-13759"
            bestGirl = "Mashiro Shiina"

            // This is a very good compoly story, meaning
            // there are a lot of truly sad moments in this
            // anime. But a lot of records have been lost and
            // idk why I'm sad. The previous impl gave AEI(5)
            AEI(7.5, Emotion.CU)

            // The group is kinda comfy ig
            NEI(5.0, Emotion.MP)

            // This and New Game! is recorded to be the
            // inspiration for me to learn programming
            // (see NewGame.kt)

            Progress(Boredom.Completed)
            Visual(VisualKind.Animated, 0.5, 0.4)

            FeatureMusic("M-VGMDB-AL-35015-1")
        }

        Entry {
            id = "M-VGMDB-AL-35015"

            Visual(VisualKind.AlbumArt, 0.5, 0.4)
            SubIDEntry("1") {
                Music(2.25)
            }
        }
    }
}
