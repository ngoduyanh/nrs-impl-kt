package com.dah.nrs

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

fun DSLScope.ShinkaiMovies() {
    Entry {
        id = "F-VGMDB-4615"
        title = "Kimi no Na wa"
        // TODO: add music ig, too lazy to do that tho (and the music sucks)

        Entry {
            id = "A-MAL-32281"
            title = "Kimi no Na wa." // generated(fill_anime_metadata.dart v0.1.1)
            idAniDB = 32281 // generated(fill_anime_metadata.dart v0.1.1)
            idKitsu = 11614 // generated(fill_anime_metadata.dart v0.1.1)
            idAniList = 21519 // generated(fill_anime_metadata.dart v0.1.1)
            idMAL = 32281 // generated(fill_anime_metadata.dart v0.1.1)

            Visual(VisualKind.Animated, 0.75, 0.75)

            bestGirl = "Miki Okudera" // generated(generate_best_girls.dart v0.1.0)
            AdditionalImpact("Compensation for KnK-YrNa jealousy", 0.75)
            AnimeConsumedProgress(EntryStatus.Completed, 0.75, 1, 1.hours + 46.minutes)
        }
    }

    Entry {
        id = "F-VGMDB-7292"
        title = "Tenki no Ko"

        Entry {
            id = "A-MAL-38826"
            title = "Tenki no Ko" // generated(fill_anime_metadata.dart v0.1.1)
            idAniDB = 38826 // generated(fill_anime_metadata.dart v0.1.1)
            idKitsu = 42028 // generated(fill_anime_metadata.dart v0.1.1)
            idAniList = 106286 // generated(fill_anime_metadata.dart v0.1.1)
            idMAL = 38826 // generated(fill_anime_metadata.dart v0.1.1)
            bestGirl = "Hina Amano" // generated(generate_best_girls.dart v0.1.0)

            Visual(VisualKind.Animated, 0.75, 0.75)
            AnimeConsumedProgress(EntryStatus.Completed, 1.0, 1, 1.hours + 52.minutes)
            FeatureMusic("M-VGMDB-AL-87003")
        }

        Entry {
            id = "M-VGMDB-AL-87003"
            title = "Tenki no Ko / RADWIMPS" // generated(fill_music_metadata.dart v0.1.1)

            Visual(VisualKind.AlbumArt, 0.5, 0.5)
            SubIDEntry("28") {
                MusicConsumedProgress("3:08") // generated(fill_music_metadata.dart v0.1.1)
                title = "Grand Escape (Movie edit) feat, Miura Toko" // generated(fill_music_metadata.dart v0.1.1)
                Music(0.5)
                OsuSong(personal = 0.4)
            }
        }
    }
}
