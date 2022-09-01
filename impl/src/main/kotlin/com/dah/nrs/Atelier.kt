package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.Atelier() {
    // Asuiro Koi Moyou and its consequences have been a disaster for our modern culture
    Entry {
        // fuck this shit
        // https://atelier.fandom.com/wiki/Miruca_Crotze
        // https://atelier.fandom.com/wiki/Escha_Malier
        // https://atelier.fandom.com/wiki/Luis_Bester

        id = "F-VGMDB-694"
        title = "Atelier"

        AKMEraPart2(0.75) {
            contributors["A-MAL-21167"] = 0.4
            contributors["G-VGMDB-1880"] = 0.3
            contributors["G-VGMDB-1090"] = 0.2
            contributors["G-VGMDB-3499"] = 0.1
        }

        // will be EHI when I finished the Dusk series
        AEI(5.0, Emotion.AP) {
            contributors["A-MAL-21167"] = 0.4
            contributors["G-VGMDB-1880"] = 0.35
            contributors["G-VGMDB-1090"] = 0.25
        }

        Entry {
            id = "A-MAL-21167"    // generated
            idMAL = 21167
            idAniList = 20506
            idAniDB = 10241
            idKitsu = 8091
            title = "Escha & Logy no Atelier: Tasogare no Sora no Renkinjutsushi"

            bestGirl = "Malier Escha"

            // this is watched in parallel with that hina ridaka romocom seasonal show
            // for the ultimate "aufripe ioek yoamou" experience
            Progress(Boredom.Watching, 9)

            // this anime somewhat feels like those rst-inspired-CP-based anime ngl
            // NEI(0.3, Emotion.CP)

            FeatureMusic("M-VGMDB-AL-45361-1")

            Visual(VisualKind.Animated, 0.4, 0.25)

            // ig this is killed somewhat similarly to luminous witches
            KilledBy("F-VGMDB-7059", potential = 0.5, effect = 0.25)
        }

        Entry {
            id = "M-VGMDB-AL-45361"
            title = "Atelier Escha & Logy ~Alchemist of Dusk Sky~ Original Soundtrack the Animation"

            Visual(VisualKind.AlbumArt, 0.4, 0.25)
            SubIDEntry("1", "Asuiro") {
                // STOP POSTING ABOUT Asuiro Koi Moyou! I'M TIRED OF SEEING IT!
                // MY FRIENDS ON TIKTOK SEND ME Rina Hidaka, ON DISCORD IT'S FUCKING Rie Marukawa!
                // I was in a server, right? and ALL OF THE CHANNELS were just Criticrista stuff.
                // I-I showed a new anime to my girlfriend and t-the OP when I read the title
                // and I said "haha its an asuiro koi moyou reference omegalul kOUkAi sHinAi yO kImI NI dEAeTa kOTo"
                // I fucking looked at a osugame jump pattern and said "THAT'S A SHIT MOYOU"
                // (TL note: "moyou" is "pattern" in japanese)
                // I looked at my penis I think of that girl from cote with the same VA as rosia-chan 3537
                // and I go "Arisu? More like Asuir" AAAAAAAAAAAAAAHGESFG

                // all of that aside, this song slaps
                Music(6.0)
            }
        }

        Entry {
            // i started the game oops
            id = "G-VGMDB-3499"
            title = "Sophie no Atelier ~Fushigi na Hon no Renkinjutsushi~"

            // Romaji name is canon
//            title = "Atelier Sophie ~The Alchemist of the Mysterious Book~"

            bestGirl = "Wise Spirit"
            // "sach cho" my beloved

            Visual(VisualKind.RPG3DGame, 0.5, 0.4)
            Visual(VisualKind.VisualNovel, 0.4, 0.3)
            Progress(Boredom.Completed)
        }

        Entry {
            id = "G-VGMDB-1090"
            title = "Ayesha no Atelier: Tasogare no Daichi no Renkinjutsushi"
            // "not even at the start line"

//            bestGirl = "Harry Maguire"
//            bestGirl = "Fred dai de"
            // atelier games visual comes in two part
            Visual(VisualKind.RPG3DGame, 0.6, 0.4)
            Visual(VisualKind.VisualNovel, 0.5, 0.3)
            Progress(Boredom.Completed)

            // i hate "bủ nio" (it's a meme lol)
            NEI(8.0, Emotion.AU to 0.5, Emotion.AP to 0.5)
        }

        Entry {
            id = "G-VGMDB-1880"
            title = "Escha & Logy no Atelier ~Tasogare no Sora no Renkinjutsushi~"
            // fucking finally

            bestGirl = "Malier Escha"
            // I heard somewhere that “Love strikes so suddenly”
            // I thought I was indifferent and I held back my feelings

            // obligatory potential waifu buff
            Visual(VisualKind.RPG3DGame, 0.7, 0.4)
            Visual(VisualKind.VisualNovel, 0.6, 0.3)
            // press f to pay respect for our tranny (me)
            // rip (btw i started the escha logy game on my birthday lol)
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            // fuck
            Waifu("Malier Escha", "2022-08-31" to today())
            ValidatorSuppress("dah-uncompleted-no-progress")
            Progress(Boredom.Watching)
        }
    }
}
