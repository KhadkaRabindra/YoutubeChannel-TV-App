package com.borkor.shobizandoid.data.response

import com.google.gson.annotations.SerializedName


data class PopularVideoDto(

    @field:SerializedName("etag")
    val etag: String? = null,

    @field:SerializedName("items")
    val items: List<Item?>? = null,

    @field:SerializedName("kind")
    val kind: String? = null,

    @field:SerializedName("prevPageToken")
    val prevPageToken: String? = null,

    @field:SerializedName("nextPageToken")
    val nextPageToken: String? = null,

    @field:SerializedName("pageInfo")
    val pageInfo: PageInfo? = null
) {

    data class Item(

        @field:SerializedName("etag")
        val etag: String? = null,

        @field:SerializedName("id")
        val id: ItemId? = null,

        @field:SerializedName("kind")
        val kind: String? = null,

        @field:SerializedName("snippet")
        val snippet: Snippet? = null
    ) {

        data class ItemId(
            @field:SerializedName("kind")
            val kind: String? = null,

            @field:SerializedName("videoId")
            val videoId: String? = null,
        )

        data class Snippet(

            @field:SerializedName("categoryId")
            val categoryId: String? = null,

            @field:SerializedName("channelId")
            val channelId: String? = null,

            @field:SerializedName("channelTitle")
            val channelTitle: String? = null,

            @field:SerializedName("defaultAudioLanguage")
            val defaultAudioLanguage: String? = null,

            @field:SerializedName("defaultLanguage")
            val defaultLanguage: String? = null,

            @field:SerializedName("description")
            val description: String? = null,

            @field:SerializedName("liveBroadcastContent")
            val liveBroadcastContent: String? = null,

            @field:SerializedName("localized")
            val localized: Localized? = null,

            @field:SerializedName("publishedAt")
            val publishedAt: String? = null,

            @field:SerializedName("tags")
            val tags: List<String?>? = null,

            @field:SerializedName("thumbnails")
            val thumbnails: Thumbnails? = null,

            @field:SerializedName("title")
            val title: String? = null
        ) {


            data class Localized(
                @field:SerializedName("description")
                val description: String? = null,

                @field:SerializedName("title")
                val title: String? = null
            )


            data class Thumbnails(

                @field:SerializedName("default")
                val default: Default? = null,

                @field:SerializedName("high")
                val high: High? = null,

                @field:SerializedName("maxres")
                val maxres: Maxres? = null,

                @field:SerializedName("medium")
                val medium: Medium? = null,

                @field:SerializedName("standard")
                val standard: Standard? = null
            ) {


                data class Default(

                    @field:SerializedName("height")
                    val height: Int? = null,

                    @field:SerializedName("url")
                    val url: String? = null,

                    @field:SerializedName("width")
                    val width: Int? = null
                )


                data class High(

                    @field:SerializedName("height")
                    val height: Int? = null,

                    @field:SerializedName("url")
                    val url: String? = null,

                    @field:SerializedName("width")
                    val width: Int? = null
                )


                data class Maxres(

                    @field:SerializedName("height")
                    val height: Int? = null,

                    @field:SerializedName("url")
                    val url: String? = null,

                    @field:SerializedName("width")
                    val width: Int? = null
                )


                data class Medium(

                    @field:SerializedName("height")
                    val height: Int? = null,

                    @field:SerializedName("url")
                    val url: String? = null,

                    @field:SerializedName("width")
                    val width: Int? = null
                )


                data class Standard(
                    @field:SerializedName("height")
                    val height: Int? = null,

                    @field:SerializedName("url")
                    val url: String? = null,

                    @field:SerializedName("width")
                    val width: Int? = null
                )
            }
        }
    }


    data class PageInfo(

        @field:SerializedName("resultsPerPage")
        val resultsPerPage: Int?,

        @field:SerializedName("totalResults")
        val totalResults: Int?
    )
}