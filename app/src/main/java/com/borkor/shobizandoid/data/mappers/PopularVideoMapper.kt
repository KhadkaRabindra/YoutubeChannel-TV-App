package com.borkor.shobizandoid.data.mappers

import com.borkor.shobizandoid.utils.Mapper
import com.borkor.shobizandoid.data.response.PopularVideoDto
import com.borkor.shobizandoid.domain.model.PopularVideo
import javax.inject.Inject

class PopularVideoMapper @Inject constructor() : Mapper<PopularVideoDto.Item, PopularVideo.Item> {
    override suspend fun map(input: PopularVideoDto.Item): PopularVideo.Item {
        return PopularVideo.Item(
            kind = input.kind,
            id = input.id?.videoId,
            etag = input.etag,
            snippet = PopularVideo.Item.Snippet(
                categoryId = input.snippet?.categoryId,
                channelId = input.snippet?.channelId,
                channelTitle = input.snippet?.channelTitle,
//                    defaultAudioLanguage = it?.snippet?.defaultAudioLanguage,
//                    defaultLanguage = it?.snippet?.defaultLanguage,
                description = input.snippet?.description,
                liveBroadcastContent = input.snippet?.liveBroadcastContent,
                localized = PopularVideo.Item.Snippet.Localized(
                    description = input.snippet?.localized?.description,
                    title = input.snippet?.localized?.title
                ),
                publishedAt = input.snippet?.publishedAt,
//                    tags = it?.snippet?.tags,
                thumbnails = PopularVideo.Item.Snippet.Thumbnails(
                    default = PopularVideo.Item.Snippet.Thumbnails.Default(
                        height = input.snippet?.thumbnails?.default?.height,
                        width = input.snippet?.thumbnails?.default?.width,
                        url = input.snippet?.thumbnails?.default?.url
                    ),
                    high = PopularVideo.Item.Snippet.Thumbnails.High(
                        height = input.snippet?.thumbnails?.high?.height,
                        width = input.snippet?.thumbnails?.high?.width,
                        url = input.snippet?.thumbnails?.high?.url
                    ),
//                        maxres = PopularVideo.Item.Snippet.Thumbnails.Maxres(
//                            height = it?.snippet?.thumbnails?.maxres?.height,
//                            width = it?.snippet?.thumbnails?.maxres?.width,
//                            url = it?.snippet?.thumbnails?.maxres?.url
//                        ),
                    medium = PopularVideo.Item.Snippet.Thumbnails.Medium(
                        height = input.snippet?.thumbnails?.medium?.height,
                        width = input.snippet?.thumbnails?.medium?.width,
                        url = input.snippet?.thumbnails?.medium?.url
                    ),
                    standard = PopularVideo.Item.Snippet.Thumbnails.Standard(
                        height = input.snippet?.thumbnails?.standard?.height,
                        width = input.snippet?.thumbnails?.standard?.width,
                        url = input.snippet?.thumbnails?.standard?.url
                    ),
                ),
                title = input.snippet?.title,
            )
        )
    }

    suspend fun mapAll(input: List<PopularVideoDto.Item?>?) : ArrayList<PopularVideo.Item> {
        val list = ArrayList<PopularVideo.Item>()
        input?.forEach {
            it?.let { it1 -> map(it1) }?.let { it2 -> list.add(it2) }
        }
        return list
    }

}