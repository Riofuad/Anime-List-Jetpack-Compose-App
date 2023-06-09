package com.riofuad.animelist.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.riofuad.animelist.data.AnimeRepository
import com.riofuad.animelist.model.Anime
import com.riofuad.animelist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Anime>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Anime>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllAnime(newQuery: String) {
        _query.value = newQuery
        try {
            val filteredAnime = repository.getAnime().filter {
                it.title.contains(_query.value, ignoreCase = true)
            }
            _uiState.value = UiState.Success(filteredAnime)
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message.toString())
        }
    }
}