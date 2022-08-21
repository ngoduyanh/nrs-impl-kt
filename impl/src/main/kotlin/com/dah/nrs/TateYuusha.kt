package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.TateYuusha() {
    Entry {
        id = "F-VGMDB-7166"
        title = "Tate no Yuusha no Nariagari"

        Entry {
            id = "A-MAL-35790"    // generated
            idMAL = 35790
            idAniList = 99263
            idAniDB = 13246
            idKitsu = 13593
            title = "Tate no Yuusha no Nariagari"
            // This became popular thanks to Reddit.
            // And yes, it's watched in the Reddit-era
            bestGirl = "Raphtalia"

            Progress(Boredom.Completed)

            // Anger when MC is treated unfairly (like Oregairu's 8man)
            NEI(5.0, Emotion.AU)
            Meme(0.5, 6)
            Visual(VisualKind.Animated, 0.5, 0.2)
        }

        Entry {
            id = "A-MAL-40356"    // generated
            idMAL = 40356
            idAniList = 111321
            idAniDB = 15070
            idKitsu = 42530
            title = "Tate no Yuusha no Nariagari Season 2"
            bestGirl = "Raphtalia"

            Progress(Boredom.Dropped, 3)
            KilledBy("F-VGMDB-7059", potential = 0.2, effect = 0.75)
            Visual(VisualKind.Animated, 0.4, 0.2)
        }
    }
}