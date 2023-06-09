package com.riofuad.animelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.riofuad.animelist.ui.components.BottomBar
import com.riofuad.animelist.ui.components.TopBar
import com.riofuad.animelist.ui.navigation.Screen
import com.riofuad.animelist.ui.screen.about.AboutScreen
import com.riofuad.animelist.ui.screen.detail.DetailScreen
import com.riofuad.animelist.ui.screen.home.HomeScreen
import com.riofuad.animelist.ui.theme.AnimeListTheme

@Composable
fun AnimeListApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            if (currentState != Screen.DetailAnime.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { animeTitle ->
                        navController.navigate(Screen.DetailAnime.createRoute(animeTitle))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailAnime.route,
                arguments = listOf(navArgument("animeTitle") { type = NavType.StringType })
            ) {
                val title = it.arguments?.getString("animeTitle") ?: ""
                DetailScreen(
                    animeTitle = title,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListAppPreview() {
    AnimeListTheme {
        AnimeListApp()
    }
}