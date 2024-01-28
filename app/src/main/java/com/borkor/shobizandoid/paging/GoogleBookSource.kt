package com.borkor.shobizandoid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borkor.shobizandoid.data.api.YouTubeApi
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.domain.model.PopularVideo
import javax.inject.Inject

class GoogleBooksBookSource @Inject constructor(
    private val api: YouTubeApi, private val popularVideoMapper: PopularVideoMapper
): PagingSource<Int, PopularVideo.Item>() {
    override fun getRefreshKey(state: PagingState<Int, PopularVideo.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularVideo.Item> {
        val position = params.key ?: 0
        val response = api.searchVideos(pageToken = position.toString())
        val items = response.items
        val popularVideoList = ArrayList<PopularVideo.Item>()
        items?.forEach {
            it?.let { it1 -> popularVideoMapper.map(it1) }?.let { it2 -> popularVideoList.add(it2) }
        }
        return LoadResult.Page(
            data = popularVideoList,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (response.items?.size == 0) null else position + 1
        )
    }

}