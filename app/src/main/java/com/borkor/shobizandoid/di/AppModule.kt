package com.borkor.shobizandoid.di

import com.borkor.shobizandoid.BuildConfig
import com.borkor.shobizandoid.core.Constants
import com.borkor.shobizandoid.data.api.NetworkInterceptor
import com.borkor.shobizandoid.data.api.YouTubeApi
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoginInterception(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return logging
    }

    @Provides
    @Singleton
    fun providesApiKey(): String {
        return BuildConfig.YOUTUBE_API_KEY
    }

    @Provides
    @Singleton
    fun providesOKHttpClient(
        networkInterceptor: NetworkInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(networkInterceptor)
            if (BuildConfig.DEBUG)
                addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): YouTubeApi =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTubeApi::class.java)


    @Provides
    @Singleton
    fun provideFireStore() = Firebase.firestore.collection(Constants.Firebase.YouTubeVideos)

    /*@Provides
    @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance().getReference(Constants.Firebase.YouTubeVideos)

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance().getReference(Constants.Firebase.YouTubeVideos)*/

}