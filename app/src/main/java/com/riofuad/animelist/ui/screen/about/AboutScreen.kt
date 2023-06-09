package com.riofuad.animelist.ui.screen.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.riofuad.animelist.R
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun AboutScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "https://i.ibb.co/sQRVDfQ/Iman.jpg",
                contentDescription = stringResource(id = R.string.profile_picture),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder_image),
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.profile_name),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.profile_email),
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriverItemPreview() {
    AnimeListTheme {
        AboutScreen()
    }
}