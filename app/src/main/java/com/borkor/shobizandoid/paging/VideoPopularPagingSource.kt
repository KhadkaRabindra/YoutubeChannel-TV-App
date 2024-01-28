package com.borkor.shobizandoid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borkor.shobizandoid.data.api.YouTubeApi
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.domain.video.VideoPagingKey

class VideoPopularPagingSource(
    private val initialKey: VideoPagingKey,
    private val api: YouTubeApi, private val popularVideoMapper: PopularVideoMapper
) : PagingSource<VideoPagingKey, PopularVideo.Item>() {

    companion object {
        private const val TAG = "VideoPopularPagingSource"
    }

    override fun getRefreshKey(state: PagingState<VideoPagingKey, PopularVideo.Item>): VideoPagingKey? {
        return null
    }

    override suspend fun load(params: LoadParams<VideoPagingKey>): LoadResult<VideoPagingKey, PopularVideo.Item> {
        return try {
            val requestParam = params.key ?: initialKey
            val pageToken = params.key ?: ""
            val data = api.searchVideos(
                maxResult = requestParam.pageSize.toString(),
            )

            val items = data.items
            val popularVideoList = ArrayList<PopularVideo.Item>()
            items?.forEach {
                it?.let { it1 -> popularVideoMapper.map(it1) }
                    ?.let { it2 -> popularVideoList.add(it2) }
            }

            LoadResult.Page(
                data = popularVideoList,
                prevKey = requestParam.copy(page = requestParam.page - 1),
                nextKey = createNextKey(requestParam, data.pageInfo?.totalResults ?: 0),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

    private fun createNextKey(
        currentKey: VideoPagingKey,
        totalResults: Int
    ): VideoPagingKey? {
        val currentMaxResults = currentKey.pageSize * currentKey.page
        return if (currentMaxResults < totalResults) {
            currentKey.copy(page = currentKey.page + 1)
        } else {
            null
        }
    }

}