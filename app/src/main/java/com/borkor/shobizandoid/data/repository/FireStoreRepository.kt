package com.borkor.shobizandoid.data.repository

import androidx.paging.PagingData
import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.utils.Resource
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {
    fun addYoutubeVideo(youtubeVideo: YoutubeVideo) : Flow<Resource<Boolean>>

    fun getYoutubeVideos(): Flow<PagingData<PopularVideo.Item>>
}