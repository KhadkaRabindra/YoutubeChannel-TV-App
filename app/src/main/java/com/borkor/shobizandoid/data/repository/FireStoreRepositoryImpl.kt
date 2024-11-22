package com.borkor.shobizandoid.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.borkor.shobizandoid.core.Constants.Firebase.PAGE_SIZE
import com.borkor.shobizandoid.data.mappers.FireStoreYoutubeVideoMappers
import com.borkor.shobizandoid.data.mappers.PopularVideoMapper
import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.paging.FirebasePagingSource
import com.borkor.shobizandoid.utils.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    private val collectionReference: CollectionReference,
    private val fireStoreYoutubeVideoMappers: FireStoreYoutubeVideoMappers
) :
    FireStoreRepository {
    override fun addYoutubeVideo(youtubeVideo: YoutubeVideo): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.loading())
            collectionReference.add(youtubeVideo).await()
            emit(Resource.success(true))
        }.catch { e ->
            emit(Resource.error(e))
        }
    }

    override fun getYoutubeVideos(): Flow<PagingData<PopularVideo.Item>> = Pager(
        config = PagingConfig(PAGE_SIZE.toInt(), prefetchDistance = PAGE_SIZE.toInt().div(4)),
    ) {
        FirebasePagingSource(collectionReference, fireStoreYoutubeVideoMappers)
    }.flow
}