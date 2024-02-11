package com.borkor.shobizandoid.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.foundation.lazy.list.TvLazyRow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.ui.theme.Gray93
import com.borkor.shobizandoid.ui.theme.inriaSansFamily

@Composable
fun MoreVideosScreen(viewModel: VideosViewModel, navigateToYoutubeView: (string: String) -> Unit) {

    val popularVideoList = viewModel.fireStoreVideoList.collectAsLazyPagingItems()

    DisposableEffect(true) {
        viewModel.getPopularVideoList()
        onDispose {}
    }

    TvLazyRow(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(all = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(popularVideoList.itemCount) { item ->
            popularVideoList[item]?.let {
                Column(
                    modifier = Modifier
                        .background(Gray93)
                        .wrapContentHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = Color.Blue)
                        ) {
                            if (it.id?.isNotEmpty() == true)
                                navigateToYoutubeView(it.id)
                        },
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.snippet?.thumbnails?.default?.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 8.dp,
                                    topStart = 8.dp,
                                    bottomEnd = 8.dp,
                                    bottomStart = 8.dp
                                )
                            ),
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Gray93
                            )
                            .width(250.dp),
                        color = Color.Black,
                        fontSize = 20.sp,
                        text = it.snippet?.title.orEmpty(),
                        fontFamily = inriaSansFamily, fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        when (popularVideoList.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = stringResource(id = R.string.loading)
                        )
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Text(text = "Error")
                    //Text(text = (usersData.loadState.append as LoadState.Error).error.message.toString())
                }
            }
        }
        when (popularVideoList.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = stringResource(id = R.string.loading)
                        )
                        CircularProgressIndicator(color = Color.Black)
                    }
                }

            }

            is LoadState.Error -> {
                item {
                    Text(text = "Error")
                    //Text(text = (usersData.loadState.append as LoadState.Error).error.message.toString())
                }
            }
        }
        when (popularVideoList.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = stringResource(id = R.string.loading)
                        )
                        CircularProgressIndicator(color = Color.Black)
                    }
                }

            }

            is LoadState.Error -> {
                item {
                    Text(text = "Error")
                    //Text(text = (usersData.loadState.append as LoadState.Error).error.message.toString())
                }
            }
        }
    }
}