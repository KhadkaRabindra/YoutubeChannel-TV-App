package com.borkor.shobizandoid

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp
import java.util.Arrays
import java.util.Collections

@HiltAndroidApp
class ShopBizApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}