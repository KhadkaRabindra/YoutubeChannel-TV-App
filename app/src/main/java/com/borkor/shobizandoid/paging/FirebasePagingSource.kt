package com.borkor.shobizandoid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borkor.shobizandoid.core.Constants.Firebase.PAGE_SIZE
import com.borkor.shobizandoid.data.mappers.FireStoreYoutubeVideoMappers
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class FirebasePagingSource(
    private val collectionReference: CollectionReference,
    private val fireStoreYoutubeVideoMappers: FireStoreYoutubeVideoMappers
) : PagingSource<QuerySnapshot, PopularVideo.Item>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, PopularVideo.Item>): QuerySnapshot? =
        null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, PopularVideo.Item> =
        try {
            /*val ref: CollectionReference =
                Firebase.firestore.collection(Constants.Firebase.YouTubeVideos)*/
            collectionReference.limit(PAGE_SIZE)
            val currentPage = params.key ?: collectionReference.get().await()
            val lastVisibleProduct = currentPage.documents[currentPage.size() - 1]
            val nextPage = collectionReference.startAfter(lastVisibleProduct).get().await()
            LoadResult.Page(
                data = fireStoreYoutubeVideoMappers.mapAll(currentPage.toObjects(YoutubeVideo::class.java)),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}