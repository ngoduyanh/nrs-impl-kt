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
            title = "Hop Step Jump! / SPRiNGS" // generated(fill_music_metadata.dart v0.1.1)

            Visual(VisualKind.AlbumArt, 0.75, 0.25)
            SubIDEntry("1") {
                title = "Hop Step Jump!" // generated(fill_music_metadata.dart v0.1.1)
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
            title = "Tsuioku Kaleidoscope / SPRiNGS" // generated(fill_music_metadata.dart v0.1.1)

            Visual(VisualKind.AlbumArt, 0.75, 0.25)
            SubIDEntry("1") {
                title = "純情-SAKURA-" // generated(fill_music_metadata.dart v0.1.1)
                // https://www.youtube.com/watch?v=X2Q-bCS_IRs
                Visual(VisualKind.AnimatedMV, 0.75, 0.25)
                Music(5.0)
            }

            // 2-4 are subunit songs
            SubIDEntry("2") {
                title = "ロマンスの林檎" // generated(fill_music_metadata.dart v0.1.1)
                Music(7.0)
            }

            SubIDEntry("3") {
                title = "SILENT VOICES" // generated(fill_music_metadata.dart v0.1.1)
                Music(4.5)
            }

            SubIDEntry("4") {
                title = "おはようジャポニカ" // generated(fill_music_metadata.dart v0.1.1)
                Music(6.0)
            }

            SubIDEntry("5") {
                title = "さよなら花火" // generated(fill_music_metadata.dart v0.1.1)
                Music(2.0)
            }
        }

        Meme(0.3, 45)
    }
}
