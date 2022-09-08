package com.dah.nrs

import com.dah.nrs.dsl.DSLScope
import com.dah.nrs.dsl.Entry
import com.dah.nrs.exts.*

fun DSLScope.UchiageHanabi() {
    Entry {
        id = "F-VGMDB-5919"
        title = "Uchiage Hanabi, Shita kara Miru ka? Yoko kara Miru ka?"

        Entry {
            id = "A-MAL-34498"
            title = "Uchiage Hanabi, Shita kara Miru ka? Yoko kara Miru ka?"

            // SLN rng
            AEI(1.0, Emotion.CU)

            bestGirl = "Oikawa Nazuna"

            Progress(Boredom.Completed)

            FeatureMusic("M-VGMDB-AL-69257-1")
            Visual(VisualKind.Animated, 0.4, 0.5)
        }

        Contains("M-VGMDB-AL-69257-1")
    }
}