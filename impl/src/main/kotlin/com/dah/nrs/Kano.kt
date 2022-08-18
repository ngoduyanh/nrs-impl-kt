package com.dah.nrs

import com.dah.nrs.dsl.DSLScope
import com.dah.nrs.dsl.Entry
import com.dah.nrs.dsl.SubIDEntry
import com.dah.nrs.dsl.vector
import com.dah.nrs.exts.*

fun DSLScope.Kano() {
    // le meme artist (kano) has a very distinctive voice,
    // making her literally the best singer in j-pop
    // her voice are soft and loli-like, but it can also
    // convey a lot of emotions
    // which is what a "kano impact" is
    // (it will be represented as an NEI/AEI)

    Entry {
        id = "M-VGMDB-AR-11666"
        title = "Kano"

        Contains(MusicVocalImageContainFactor) {
            Entry {
                id = "M-VGMDB-AL-95369"
                title = "Bambino"

                Visual(VisualKind.AlbumArt, 0.2, 0.5)

                SubIDEntry("1", "Hello / How are you") {
                    Music(2.0)
                    NEI(1.0, Emotion.MP)
                    Remix("M-VGMDB-AL-37130-1")
                }
            }

            Entry {
                id = "M-VGMDB-AL-34411"
                title = "Aimai Bambina"

                Visual(VisualKind.AlbumArt, 0.35, 0.4)

                SubIDEntry("6", "Interviewer") {
                    Music(4.0)
                    NEI(1.5, Emotion.CU)
                }

                SubIDEntry("11", "Irony") {
                    Music(1.75)
                    NEI(1.5, Emotion.CU)
                }
            }

            Entry {
                id = "M-VGMDB-AL-42961"
                title = "Arumachi no Hakuchumu"

                Visual(VisualKind.AlbumArt, 0.25, 0.5)

                Impact {
                    description = "Romance name"
                    score = vector {
                        set(Art.Language, 0.1)
                    }
                }

                SubIDEntry("6", "Mary Mary (album ver.)") {
                    Music(4.0)
                    NEI(2.5, Emotion.MP)
                }

                SubIDEntry("9", "World on Color") {
                    Music(6.0)
                    NEI(2.0, Emotion.MP)
                    OsuSong(personal = 1.0, community = 0.0)
                }
            }

            Entry {
                id = "M-VGMDB-AL-49423"
                title = "Good Hello"

                Visual(VisualKind.AlbumArt, 0.55, 0.45)

                SubIDEntry("1", "Good Night Everyone") {
                    Music(4.0)
                    NEI(2.0, Emotion.CU)
                }

                SubIDEntry("8", "decide") {
                    Music(2.0)
                    NEI(2.5, Emotion.CU)
                }
            }

            Entry {
                id = "M-VGMDB-AL-51254"
                title = "Stella-rium"

                Visual(VisualKind.AlbumArt, 0.5, 0.4)
                Visual(VisualKind.SemiAnimatedMV, 0.5, 0.3)

                SubIDEntry("1") {
                    Music(5.0)
                    NEI(0.5, Emotion.MP)
                }
            }

            Entry {
                id = "M-VGMDB-AL-54307"
                title = "Dear Brave"

                Visual(VisualKind.AlbumArt, 0.2, 0.6)
                Visual(VisualKind.AnimatedMV, 0.6, 0.4)

                SubIDEntry("1") {
                    Music(5.0)
                    NEI(1.0, Emotion.CU)
                }
            }

            Entry {
                id = "M-VGMDB-AL-57564"
                title = "nowhere"

                SubIDEntry("1", "Prima Stella") {
                    Visual(VisualKind.SemiAnimatedMV, 0.5, 0.3)
                    Music(5.0)
                    NEI(1.0, Emotion.CU)
                }

                Contains("M-VGMDB-AL-51254-1")
                Contains("M-VGMDB-AL-54307-1")
            }

            Entry {
                id = "M-VGMDB-AL-73516"
                title = "one"

                Contains("M-VGMDB-AL-34411-11")
            }

            Entry {
                id = "M-VGMDB-AL-89290"
                title = "three"

                SubIDEntry("6", "Chikyuu Saigo no Kokuhaku o") {
                    Music(3.0)
                    NEI(1.0, Emotion.CU)

                    Remix("M-MAL-36631-6")
                }
            }

            Entry {
                // from the album "Colorful Wonder Note", track number 8
                // and yes this is from osugame too
                id = "M-24"
                title = "Kimiiro Hanabi -album version-"

                Music(6.0)
                NEI(5.0, Emotion.CP)
            }

            Entry {
                // the best album of all time
                // literally the perfect combination of vocal and instrumental
                id = "M-VGMDB-AL-37130"
                title = "Sukinano."

                // the album art is kano sitting with some animals
                // which is MP-based, and it's a pretty good contrast to
                // some songs from the album (like it's not world's end
                // or sakura no zenya), which are CU-based.
                Visual(VisualKind.AlbumArt, 0.4, 0.7)
                NEI(2.0, Emotion.MP to 0.6, Emotion.CU to 0.4)
                // crossfade thing: https://www.nicovideo.jp/watch/sm19702966
                Visual(VisualKind.AnimatedMV, 0.3, 0.65)

//        Impact {
//            description = "Album art"
//
//            meta("type", "NONSTD_album_art")
//
//            score = vector {
//                set(Art.Visual, 0.1)
//                set(Emotion.MP, 0.2)
//                set(Emotion.CU, 0.1)
//            }
//        }

                SubIDEntry("1", "Hello / How are you (mix)") {
                    // the original MV is for the mix version
                    // https://www.nicovideo.jp/watch/sm19687208
                    Visual(VisualKind.SemiAnimatedMV, 0.35, 0.6)
                    Music(2.5)
                    NEI(1.25, Emotion.MP)
                }

                SubIDEntry("2", "Utautai no Uta") {
                    Music(8.0)
                    NEI(1.5, Emotion.MP)
                }

                // the best kano song ever. period.
                // also the fourth ayumu-era theme song
                SubIDEntry("3", "[It's not] World's end") {
                    Music(5.0)
                    NEI(5.0, Emotion.CU)
                }

                SubIDEntry("4", "\"Nee.\"") {
                    Music(2.0)
                    NEI(2.5, Emotion.CU)
                }

                SubIDEntry("5", "Asayake, Kimi no Uta.") {
                    Music(2.0)
                    NEI(2.0, Emotion.MP)
                }

                SubIDEntry("6", "Sakura no Zenya") {
                    Visual(VisualKind.AnimatedMV, 0.35, 0.6)
                    Music(7.0)
                    NEI(2.5, Emotion.CU)
                }
            }
            // after this, almost all of kano's songs suck
            // (if kano sees this pls keep going on the
            // path you've chosen, don't mind me)

            // oh except for this, i forgor
            Entry {
                id = "M-49"
                title = "Sore wa Kitto Natsudatta"

                Visual(VisualKind.AnimatedMV, 0.35, 0.7)
                // 210 bpm spaced bursts
                OsuSong(personal = 5.0)

                Music(5.0)
            }
        }

        // kano-era
        Meme(0.9, 90)
    }
}
