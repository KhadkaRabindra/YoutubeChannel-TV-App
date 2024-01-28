package com.borkor.shobizandoid.data.repository

import androidx.paging.PagingData
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.domain.video.VideoPagingKey
import kotlinx.coroutines.flow.Flow

interface YouTubeRepository {

    fun getPopularVideoPagingFlow(videoPagingKey: VideoPagingKey): Flow<PagingData<PopularVideo.Item>>
}