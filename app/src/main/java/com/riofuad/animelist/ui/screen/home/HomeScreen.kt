package com.riofuad.animelist.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riofuad.animelist.di.Injection
import com.riofuad.animelist.model.Anime
import com.riofuad.animelist.ui.ViewModelFactory
import com.riofuad.animelist.ui.common.UiState
import com.riofuad.animelist.ui.components.AnimeListItem
import com.riofuad.animelist.ui.components.ErrorContent
import com.riofuad.animelist.ui.components.Loading
import com.riofuad.animelist.ui.components.SearchBar
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Loading()
                viewModel.getAllAnime(query)
            }
            is UiState.Success -> {
                HomeContent(
                    animeList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::getAllAnime
                )
            }
            is UiState.Error -> ErrorContent()
        }
    }
}

@Composable
fun HomeContent(
    animeList: List<Anime>,
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        Box(modifier = modifier) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.testTag("animeList")
            ) {
                items(animeList) { data ->
                    AnimeListItem(
                        title = data.title,
                        photoUrl = data.photoUrl,
                        score = data.score,
                        genre = data.genre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigateToDetail(data.title)
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    AnimeListTheme {
        HomeScreen(navigateToDetail = {})
    }
}