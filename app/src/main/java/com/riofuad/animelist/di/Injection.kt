package com.riofuad.animelist.di

import com.riofuad.animelist.data.AnimeRepository

object Injection {
    fun provideRepository(): AnimeRepository {
        return AnimeRepository.getInstance()
    }
}