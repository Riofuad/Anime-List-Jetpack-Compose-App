package com.riofuad.animelist.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailAnime : Screen("home/{animeTitle}") {
        private fun encodeForDeepLink(string: String): String {
            return string.replace("/", "%2F")
                .replace("?", "%3F")
        }
        fun createRoute(animeTitle: String) = "home/${encodeForDeepLink(animeTitle)}"
    }
}
