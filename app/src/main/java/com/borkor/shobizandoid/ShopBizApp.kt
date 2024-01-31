package com.borkor.shobizandoid

import android.app.Application
import com.parse.Parse
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@HiltAndroidApp
class ShopBizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .clientBuilder(client.newBuilder())
                .build()
        );
    }
}