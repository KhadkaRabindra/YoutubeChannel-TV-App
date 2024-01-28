package com.borkor.shobizandoid.data.api

import com.borkor.shobizandoid.data.response.PopularVideoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("youtube/v3/search")
    suspend fun searchVideos(
        @Query("channelId") channelId: String = "UCCFdkJZWZrvkfqQVtkvYnqA",
        //@Query("channelId") channelId: String = BuildConfig.YOUTUBE_CHANNEL_ID,
        @Query("order") order: String = "date",
        @Query("maxResults") maxResult: String = "5",
        @Query("part") part: String = "snippet,id",
        @Query("pageToken") pageToken: String = ""
    ): PopularVideoDto
}