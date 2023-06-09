package com.riofuad.animelist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.riofuad.animelist.R
import com.riofuad.animelist.model.AnimeData
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun AnimeListItem(
    title: String,
    photoUrl: String,
    score: String,
    genre: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .testTag("animeItem")
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    placeholder = painterResource(R.drawable.placeholder_image),
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .size(width = 100.dp, height = 150.dp)
                )
                Column(modifier = Modifier
                    .weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 16.dp, end = 10.dp)
                    )
                    Text(
                        text = score,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = genre,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 16.dp, end = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListItemPreview() {
    AnimeListTheme {
        AnimeListItem(
            title = AnimeData.anime[0].title,
            photoUrl = AnimeData.anime[0].photoUrl,
            score = AnimeData.anime[0].score,
            genre = AnimeData.anime[0].genre,
        )
    }
}