package com.borkor.shobizandoid.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.borkor.shobizandoid.data.repository.ParseRepository
import com.borkor.shobizandoid.data.repository.YouTubeRepository
import com.borkor.shobizandoid.domain.video.VideoPagingKey
import com.borkor.shobizandoid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val youTubeRepository: YouTubeRepository,
    private val parseRepository: ParseRepository
) : ViewModel() {

    val parseFlag : MutableStateFlow<Resource<Boolean>> = MutableStateFlow(Resource.loading())

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

    fun getParseFlag(){
        parseRepository.getFlag().map {
            parseFlag.value = it
        }.launchIn(viewModelScope)
    }

}