package com.borkor.shobizandoid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.borkor.shobizandoid.data.api.YouTubeApi
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.domain.video.VideoPagingKey
import com.borkor.shobizandoid.paging.VideoPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private var apiService: YouTubeApi,
    private var popularVideoMapper: PopularVideoMapper,
) : YouTubeRepository {

    override fun getPopularVideoPagingFlow(videoPagingKey: VideoPagingKey): Flow<PagingData<PopularVideo.Item>> {
        return Pager(
            config = PagingConfig(videoPagingKey.pageSize, prefetchDistance = videoPagingKey.pageSize.div(4)),
            pagingSourceFactory = {
                //VideoPopularPagingSource(videoPagingKey, apiService, popularVideoMapper)
                VideoPagingSource(videoPagingKey, apiService, popularVideoMapper)
                //GoogleBooksBookSource(apiService, popularVideoMapper)
            }
        ).flow
    }
}