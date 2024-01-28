package com.borkor.shobizandoid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopBizApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}