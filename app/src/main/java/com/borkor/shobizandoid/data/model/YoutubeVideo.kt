package com.borkor.shobizandoid.data.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
data class YoutubeVideo(
    var videoID: String? = null,
    val title: String? = null,
    var imageURL: String? = null
)