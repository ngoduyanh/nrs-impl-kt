package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.OnsenMusume() {
    // le funny franchise, lack content therefore low score
    // translate stuff for me if u want score to go up

    Entry {
        id = "F-VGMDB-6439"
        title = "Onsen Musume"

        Entry {
            id = "M-VGMDB-AL-75349"
            title = "Hop Step Jump! / SPRiNGS" // generated by fill_metadata.dart v0.1.0

            Visual(VisualKind.AlbumArt, 0.75, 0.25)
            SubIDEntry("1") {
                title = "Hop Step Jump!" // generated by fill_metadata.dart v0.1.0
                Music(6.0)
            }
        }

        Entry {
            id = "M-17"
            title = "SPRiNGS"

            Contains(MusicVocalImageContainFactor) {
                Contains("M-VGMDB-AL-75344-1")
                Contains("M-VGMDB-AL-75344-5")
                Contains("M-VGMDB-AL-75349")
            }
        }

        Entry {
            id = "M-VGMDB-AL-75344"
            title = "Tsuioku Kaleidoscope / SPRiNGS" // generated by fill_metadata.dart v0.1.0

            Visual(VisualKind.AlbumArt, 0.75, 0.25)
            SubIDEntry("1", "Junjou -SAKURA-") {
                title = "純情-SAKURA-" // generated by fill_metadata.dart v0.1.0
                // https://www.youtube.com/watch?v=X2Q-bCS_IRs
                Visual(VisualKind.AnimatedMV, 0.75, 0.25)
                Music(5.0)
            }

            // 2-4 are subunit songs
            SubIDEntry("2", "Romance no Ringo") {
                title = "ロマンスの林檎" // generated by fill_metadata.dart v0.1.0
                Music(7.0)
            }

            SubIDEntry("3", "SILENT VOICES") {
                title = "SILENT VOICES" // generated by fill_metadata.dart v0.1.0
                Music(4.5)
            }

            SubIDEntry("4", "Ohayou Japonika") {
                title = "おはようジャポニカ" // generated by fill_metadata.dart v0.1.0
                Music(6.0)
            }

            SubIDEntry("5", "Sayonara Hanabi") {
                title = "さよなら花火" // generated by fill_metadata.dart v0.1.0
                Music(2.0)
            }
        }

        Meme(0.3, 45)
    }
}
