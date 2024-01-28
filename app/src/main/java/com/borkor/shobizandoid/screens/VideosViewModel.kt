package com.borkor.shobizandoid.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.borkor.shobizandoid.data.repository.YouTubeRepository
import com.borkor.shobizandoid.domain.video.VideoPagingKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val youTubeRepository: YouTubeRepository,
) : ViewModel() {

    private val popularVideoPaging: MutableSharedFlow<VideoPagingKey> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val popularVideoList = popularVideoPaging
        .flatMapLatest { pagingKey -> youTubeRepository.getPopularVideoPagingFlow(pagingKey) }
        .cachedIn(viewModelScope)


    fun getPopularVideoList() {
        viewModelScope.launch {
            popularVideoPaging.emit(VideoPagingKey())
        }
    }

}