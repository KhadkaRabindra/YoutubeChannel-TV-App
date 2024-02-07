package com.borkor.shobizandoid.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.borkor.shobizandoid.screens.AboutScreen
import com.borkor.shobizandoid.screens.HelpAndFeedbackScreen
import com.borkor.shobizandoid.screens.HomeScreen
import com.borkor.shobizandoid.screens.PrivacyScreen
import com.borkor.shobizandoid.screens.VideosViewModel
import com.borkor.shobizandoid.screens.WebViewScreen
import com.borkor.shobizandoid.screens.YoutubeScreen
import com.borkor.shobizandoid.screens.YoutubeVideoPlayer
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation(
    navController: NavHostController,
    drawerState: DrawerState,
    videoViewModel: VideosViewModel
) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        Screens.values().forEach { screen ->
            composable(screen.route) {
                when (screen) {
                    Screens.HomeScreen -> HomeScreen(drawerState = drawerState, videoViewModel, {
                        navController.navigate(Screens.WebViewScreen.route)
                    }, {
                        navController.navigate(
                            Screens.YoutubeScreen.route.replace(
                                "{videoID}",
                                it
                            )
                        )
                    })

                    Screens.WebViewScreen -> WebViewScreen()
                    Screens.YoutubeScreen -> {
                        val videoID = it.arguments?.getString("videoID")
                        YoutubeVideoPlayer(modifier = Modifier,
                            videoId = videoID.orEmpty(),
                            isPlaying = {},
                            isLoading = {},
                            onVideoEnded = {}
                        )
                    }

                    Screens.PrivacyScreen -> PrivacyScreen {
                        navController.popBackStack()
                    }

                    Screens.SystemUpdateScreen -> {

                    }

                    Screens.HelpAndFeedbackScreen -> HelpAndFeedbackScreen {
                        navController.popBackStack()
                    }

                    Screens.AboutScreen -> AboutScreen {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

