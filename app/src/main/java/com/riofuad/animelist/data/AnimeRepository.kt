package com.riofuad.animelist.data

import com.riofuad.animelist.model.Anime
import com.riofuad.animelist.model.AnimeData

class AnimeRepository {
    private val animeList = mutableListOf<Anime>()

    init {
        if (animeList.isEmpty()) {
            AnimeData.anime.forEach {
                animeList.add(it)
            }
        }
    }

    fun getAnime(): List<Anime> {
        return AnimeData.anime
    }

    fun getAnimeByTitle(title: String): Anime {
        return animeList.first {
            it.title == title
        }
    }

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(): AnimeRepository =
            instance ?: synchronized(this) {
                AnimeRepository().apply {
                    instance = this
                }
            }
    }
}