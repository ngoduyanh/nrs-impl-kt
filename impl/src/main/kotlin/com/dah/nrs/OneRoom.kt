package com.dah.nrs

fun GenerateBlock.OneRoom() {
    // (1) - - - - - - - (2)
    // (1)        (1)       (1)
    //    \        |        /
    //     \       |       /
    //      \      |      /
    //      (2)   (2)   (2)

    Entry {
        id = "F-VGMDB-6434"
        title = "OneRoom"

        Entry {
            id = "M-VGMDB-AL-63666"
            title = "harumachi clover"

            // ME NO MAE NO TOBIRA O AKETARA HARU KAZE
            // TORI TACHI MO KIGI DE MACHIAWASE
            // KIMI E MUKAU SHINGOU AOZORA IRO
            // KAKE DAZEBA II
            // USOTSUKI KAKURITSU RON TOKA
            // ICHI PURASU ICHI GA MUGEN TOKA
            // OSHIETE KURETA KIMI TO SAGASHI NI YUKOU
            // H A R U M A C H I   K U R O B A A A
            Music(2.5)
            OsuSong(personal = 6.0, community = 8.0)
            Remix("M-37")
        }

        Entry {
            id = "A-MAL-34392"
            title = "One Room"
            bestGirl = "Hanasaka Yui"
            seasonal = true

            Boredom(Boredom.Dropped)
            FeatureMusic("M-VGMDB-AL-63666-1")
        }
    }

    Entry {
        id = "M-37"
        title = "Harumachi Clover (Swing Arrangement) [Dictate Edit]"

        Music(1.0)
        OsuSong(personal = 6.0, community = 8.0)
    }
}