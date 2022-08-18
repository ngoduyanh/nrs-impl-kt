package com.dah.nrs

import com.dah.nrs.dsl.DSLScope
import com.dah.nrs.dsl.Entry
import com.dah.nrs.dsl.SubIDEntry
import com.dah.nrs.dsl.vector
import com.dah.nrs.exts.*

fun DSLScope.JMetal() {
    Entry {
        id = "M-VGMDB-AR-8482"
        title = "Foreground Eclipse"

        Entry {
            id = "M-VGMDB-AL-97601"
            title = "Truths, Ironies, The Secret Lyrics"

            Visual(VisualKind.AlbumArt, 0.15, 0.3)

            SubIDEntry("1") {
                Music(3.0)
                Impact {
                    description = """this song is a cover of the famous is she owen u.n
                    i was strongly impressed when i listened to the covers by
                    cool&create silverforest s-sync arts and sound holic
                    but its hard to rearrange cuz its melody's so complex
                    one more day before you go one more night everybody dance it away
                    swinging arms jumping bodies dont stop even if night's out
                    (stream part)
                    i wrote this song in a hurry
                    i know this lyric is funny
                    i wish at least one of you like this song thats all""".trimIndent()
                    score = vector {
                        set(Art.Language, 0.2)
                    }
                }
            }
        }

        // this and HAG - Shoujotachi no Owaranai Yoru (lit. Everlasting Night of Teenage Girls)
        // somewhat reflect an image of "happy nights" in Ayumu-era
        Impact {
            description = "Ayumu-era Romance Image"
            score = vector {
                set(Art.Language, 0.1)
            }

            contributors["M-VGMDB-AL-43320"] = 0.4
        }

        Entry {
            id = "M-VGMDB-AL-43320"
            title = "Stories That Last Through The Sleepless Nights"

            Contains("M-VGMDB-AL-97601-1")
            Visual(VisualKind.AlbumArt, 0.25, 0.4)

            SubIDEntry("4") {
                title = "Wandering, Never Wondering (There Exists A Shade)"
                Music(2.5)
            }

            SubIDEntry("7") {
                title = "From Under Cover (Caught Up In A Love Song)"
                Music(4.0)
            }

            // the second ayumu-era theme song
            SubIDEntry("8") {
                title = "Storytellers"
                Music(5.0)
            }
        }
    }
}