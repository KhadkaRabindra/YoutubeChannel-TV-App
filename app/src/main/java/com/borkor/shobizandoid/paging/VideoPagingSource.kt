package com.borkor.shobizandoid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borkor.shobizandoid.data.api.YouTubeApi
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.domain.video.VideoPagingKey

class VideoPagingSource(
    private val initialKey: VideoPagingKey,
    private val api: YouTubeApi,  // Replace with your YouTube API implementation
    private val popularVideoMapper: PopularVideoMapper
) : PagingSource<String, PopularVideo.Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularVideo.Item> {
        try {
            val nextPageToken = params.key ?: ""
            val response = api.searchVideos(
                maxResult = params.loadSize.toString(),
                pageToken = nextPageToken,
            )
            return LoadResult.Page(
                data = popularVideoMapper.mapAll(response.items),
                prevKey = response.prevPageToken,  // Pagination in YouTube API doesn't support previous pages
                nextKey = response.nextPageToken
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PopularVideo.Item>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}