package com.borkor.shobizandoid.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.lifecycle.Lifecycle
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.screens.common.OnLifecycleEvent
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.launch

@Composable
fun InterstitialAdsScreen(context: Context, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var mInterstitialAd: InterstitialAd? = null
        val btnText = remember { mutableStateOf("Loading interstitial Ad") }
        val btnEnable = remember { mutableStateOf(false) }

        fun loadInterstitialAd(context: Context) {
            InterstitialAd.load(context,
                context.getString(R.string.interstitial_ads_id),
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                        btnText.value = "Show interstitial Ad"
                        btnEnable.value = true
                    }
                }
            )
        }

        fun showInterstitialAd(context: Context, onAdDismissed: () -> Unit) {
            if (mInterstitialAd != null) {
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdFailedToShowFullScreenContent(e: AdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdDismissedFullScreenContent() {
                        mInterstitialAd = null

                        loadInterstitialAd(context)
                        onAdDismissed()

                        btnText.value = "Loading interstitial Ad"
                        btnEnable.value = false
                    }
                }
                mInterstitialAd?.show(context as Activity)
            }
        }

        loadInterstitialAd(context)

        OnLifecycleEvent { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                }
                Lifecycle.Event.ON_CREATE ->{
                    coroutineScope.launch {
                        showInterstitialAd(context) {
                            Toast.makeText(context, "Interstitial Ad Shown!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else -> {
                }
            }
        }
    }
}