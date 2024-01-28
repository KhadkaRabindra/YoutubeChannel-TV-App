package com.borkor.shobizandoid.nav

import com.borkor.shobizandoid.R

enum class Screens(val route: String) {
    HomeScreen("home"),
    WebViewScreen("web_view"),
    YoutubeScreen("youtube/{videoID}"),
    PrivacyScreen("privacy"),
    SystemUpdateScreen("system_update"),
    HelpAndFeedbackScreen("help_and_feedback"),
    AboutScreen("about"),
}

data class NavItems(
    val title: Int,
    val description: Int,
    val icon: Int,
    val route: String
)

val listOfNavItems = listOf(
    NavItems(
        title = R.string.privacy,
        description = R.string.privacy_description,
        icon = R.drawable.privacy,
        route = Screens.PrivacyScreen.route
    ),
    NavItems(
        title = R.string.system_update,
        description = R.string.system_update_description,
        icon = R.drawable.system_update,
        route = Screens.SystemUpdateScreen.route
    ),
    NavItems(
        title = R.string.help_and_feedback,
        description = R.string.help_and_feedback_description,
        icon = R.drawable.help_and_feedback,
        route = Screens.HelpAndFeedbackScreen.route
    ),
    NavItems(
        title = R.string.about,
        description = R.string.about_description,
        icon = R.drawable.about,
        route = Screens.AboutScreen.route
    ),

    )