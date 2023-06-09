package com.riofuad.animelist.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riofuad.animelist.R
import com.riofuad.animelist.di.Injection
import com.riofuad.animelist.model.Anime
import com.riofuad.animelist.model.AnimeData
import com.riofuad.animelist.ui.ViewModelFactory
import com.riofuad.animelist.ui.common.UiState
import com.riofuad.animelist.ui.components.AnimeDetailBanner
import com.riofuad.animelist.ui.components.AnimeDetailDescription
import com.riofuad.animelist.ui.components.ErrorContent
import com.riofuad.animelist.ui.components.Loading
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun DetailScreen(
    animeTitle: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Loading()
                viewModel.getAnimeByTitle(animeTitle)
            }
            is UiState.Success -> {
                DetailContent(
                    anime = uiState.data,
                    modifier = modifier,
                    navigateBack = navigateBack
                )
            }
            is UiState.Error -> ErrorContent()
        }
    }
}

@Composable
fun DetailContent(
    anime: Anime,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = modifier) {
            AnimeDetailBanner(
                title = anime.title,
                photo = anime.photoUrl,
                studio = anime.studio
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.icon_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
            )
        }
        AnimeDetailDescription(
            genre = anime.genre,
            score = anime.score,
            synopsis = anime.synopsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    AnimeListTheme {
        DetailContent(
            anime = AnimeData.anime[0],
            navigateBack = {}
        )
    }
}