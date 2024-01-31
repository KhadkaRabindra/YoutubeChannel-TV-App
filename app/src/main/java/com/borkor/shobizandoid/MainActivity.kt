package com.borkor.shobizandoid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.borkor.shobizandoid.screens.ErrorScreen
import com.borkor.shobizandoid.screens.MainScreen
import com.borkor.shobizandoid.screens.VideosViewModel
import com.borkor.shobizandoid.ui.theme.ShowBizTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.parse.ParseObject
import com.parse.ParseQuery
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: VideosViewModel by viewModels()

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        val configuration = RequestConfiguration.Builder()
            //.setTestDeviceIds(Collections.singletonList("775EC382F63089447F3FA3C657F9FEBA"))
            .setTestDeviceIds(listOf("775EC382F63089447F3FA3C657F9FEBA"))
            .build()
        MobileAds.setRequestConfiguration(configuration)

        val query = ParseQuery.getQuery<ParseObject>("AppSettings")
        query.getInBackground("ZKFQGHhkn8") { appSettings, e ->
            setContent {
                ShowBizTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RectangleShape
                    ) {
                        if (e == null) {
                            // object will be your game score
                            val flag = appSettings.getBoolean("flag")
                            if (flag){
                                MainScreen(viewModel)
                            }else{
                                ErrorScreen()
                            }

                        } else {
                            ErrorScreen()
                        }
                    }
                }
            }
        }
    }
}
