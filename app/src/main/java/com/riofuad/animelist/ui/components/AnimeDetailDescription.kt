package com.riofuad.animelist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.riofuad.animelist.model.AnimeData
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun AnimeDetailDescription(
    genre: String,
    score: String,
    synopsis: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)) {
        Column() {
            Text(
                text = genre,
                style = MaterialTheme.typography.subtitle1
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color(0xFFFF9800),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
                Text(
                    text = score,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
            Text(
                text = synopsis,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeDetailDescriptionPreview() {
    AnimeListTheme {
        AnimeDetailDescription(
            genre = AnimeData.anime[0].genre,
            score = AnimeData.anime[0].score,
            synopsis = AnimeData.anime[0].synopsis
        )
    }
}