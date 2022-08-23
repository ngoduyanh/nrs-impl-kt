package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.ShinkaiMovies() {
    Entry {
        id = "F-VGMDB-4615"
        title = "Kimi no Na wa"
        // TODO: add music ig, too lazy to do that tho (and the music sucks)

        Entry {
            id = "A-MAL-32281"    // generated
            idMAL = 32281
            idAniList = 21519
            idAniDB = 11829
            idKitsu = 11614
            title = "Kimi no Na wa"

            Visual(VisualKind.Animated, 0.75, 0.75)

            bestGirl = "Miki Okudera"
            AdditionalImpact("Compensation for KnK-YrNa jealousy", 0.75)
            Progress(Boredom.Completed)
        }
    }

    Entry {
        id = "F-VGMDB-7292"
        title = "Tenki no Ko"

        Entry {
            id = "A-MAL-38826"    // generated
            idMAL = 38826
            idAniList = 106286
            idKitsu = 42028
            idAniDB = 14534
            title = "Tenki no Ko"
            bestGirl = "Amano Hina"

            Visual(VisualKind.Animated, 0.75, 0.75)
            Progress(Boredom.Completed)
            FeatureMusic("M-VGMDB-AL-87003")
        }

        Entry {
            id = "M-VGMDB-AL-87003"
            title = "Tenki no Ko"

            Visual(VisualKind.AlbumArt, 0.5, 0.5)
            SubIDEntry("28", "Grand Escape (Movie Edit) [feat. Toko Miura]") {
                Music(5.0)
                OsuSong(personal = 4.0)
            }
        }
    }
}