package com.borkor.shobizandoid.data.mappers

import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.domain.model.PopularVideo
import com.borkor.shobizandoid.utils.Mapper
import javax.inject.Inject

class FireStoreYoutubeVideoMappers @Inject constructor() :
    Mapper<YoutubeVideo, PopularVideo.Item> {
    override suspend fun map(input: YoutubeVideo): PopularVideo.Item {
        return PopularVideo.Item(
            kind = "",
            id = input.videoID,
            etag = "",
            snippet = PopularVideo.Item.Snippet(
                categoryId = "",
                channelId = "",
                channelTitle = input.title,
//                    defaultAudioLanguage = it?.snippet?.defaultAudioLanguage,
//                    defaultLanguage = it?.snippet?.defaultLanguage,
                description = "",
                liveBroadcastContent = "",
                localized = PopularVideo.Item.Snippet.Localized(
                    description = "",
                    title = ""
                ),
                publishedAt = "",
//                    tags = it?.snippet?.tags,
                thumbnails = PopularVideo.Item.Snippet.Thumbnails(
                    default = PopularVideo.Item.Snippet.Thumbnails.Default(
                        height = 0,
                        width = 0,
                        url = input.imageURL,
                    ),
                    high = PopularVideo.Item.Snippet.Thumbnails.High(
                        height = 0,
                        width = 0,
                        url = ""
                    ),
//                        maxres = PopularVideo.Item.Snippet.Thumbnails.Maxres(
//                            height = it?.snippet?.thumbnails?.maxres?.height,
//                            width = it?.snippet?.thumbnails?.maxres?.width,
//                            url = it?.snippet?.thumbnails?.maxres?.url
//                        ),
                    medium = PopularVideo.Item.Snippet.Thumbnails.Medium(
                        height = 0,
                        width = 0,
                        url = ""
                    ),
                    standard = PopularVideo.Item.Snippet.Thumbnails.Standard(
                        height = 0,
                        width = 0,
                        url = ""
                    ),
                ),
                title = "",
            )
        )
    }

    suspend fun mapAll(input: List<YoutubeVideo>?): ArrayList<PopularVideo.Item> {
        val list = ArrayList<PopularVideo.Item>()
        input?.forEach {
            it.let { it1 -> map(it1) }.let { it2 -> list.add(it2) }
        }
        return list
    }
}