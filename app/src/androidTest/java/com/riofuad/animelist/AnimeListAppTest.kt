package com.riofuad.animelist

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.riofuad.animelist.model.AnimeData
import com.riofuad.animelist.ui.navigation.Screen
import com.riofuad.animelist.ui.theme.AnimeListTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeListAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AnimeListTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                AnimeListApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("animeList").performScrollToIndex(40)
        composeTestRule.onNodeWithText(AnimeData.anime[40].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailAnime.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.icon_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun search_anime_does_not_exist_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_anime).performClick().performTextInput("Anime Test")
        composeTestRule.onNodeWithTag("animeItem").assertDoesNotExist()
    }

    @Test
    fun search_anime_exist_and_go_to_the_detail_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_anime).performClick().performTextInput(AnimeData.anime[0].title)
        composeTestRule.onNodeWithTag("animeItem").performClick()
        navController.assertCurrentRouteName(Screen.DetailAnime.route)
        composeTestRule.onNodeWithText(AnimeData.anime[0].title).assertIsDisplayed()
        composeTestRule.onNodeWithText(AnimeData.anime[0].studio).assertIsDisplayed()
        composeTestRule.onNodeWithText(AnimeData.anime[0].genre).assertIsDisplayed()
        composeTestRule.onNodeWithText(AnimeData.anime[0].score).assertIsDisplayed()
        composeTestRule.onNodeWithText(AnimeData.anime[0].synopsis).assertIsDisplayed()
    }

    @Test
    fun search_anime_exist_and_go_to_the_wrong_detail_success() {
        composeTestRule.onNodeWithTag("search").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.search_anime).performClick().performTextInput(AnimeData.anime[0].title)
        composeTestRule.onNodeWithTag("animeItem").performClick()
        navController.assertCurrentRouteName(Screen.DetailAnime.route)
        composeTestRule.onNodeWithText(AnimeData.anime[1].title).assertDoesNotExist()
        composeTestRule.onNodeWithText(AnimeData.anime[1].studio).assertDoesNotExist()
        composeTestRule.onNodeWithText(AnimeData.anime[1].genre).assertDoesNotExist()
        composeTestRule.onNodeWithText(AnimeData.anime[1].score).assertDoesNotExist()
        composeTestRule.onNodeWithText(AnimeData.anime[1].synopsis).assertDoesNotExist()
    }
}