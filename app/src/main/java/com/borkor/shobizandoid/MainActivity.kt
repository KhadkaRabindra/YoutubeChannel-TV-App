package com.borkor.shobizandoid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.borkor.shobizandoid.screens.ErrorScreen
import com.borkor.shobizandoid.screens.MainScreen
import com.borkor.shobizandoid.screens.VideosViewModel
import com.borkor.shobizandoid.screens.common.SplashScreen
import com.borkor.shobizandoid.ui.theme.ShowBizTheme
import com.borkor.shobizandoid.utils.DataStatus
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
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

        setContent {
            ShowBizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    val parseFlag by viewModel.parseFlag.collectAsState()
                    DisposableEffect(Unit) {
                        viewModel.getParseFlag()
                        onDispose {
                        }
                    }

                    when (parseFlag.status){
                        DataStatus.SUCCESS ->{
                            if (parseFlag.data == true)
                                MainScreen(viewModel)
                            else{
                                ErrorScreen()
                            }
                        }
                        DataStatus.ERROR ->{
                            ErrorScreen()
                        }
                        DataStatus.LOADING ->{
                            SplashScreen()
                        }
                    }
                }
            }
        }
    }
}
