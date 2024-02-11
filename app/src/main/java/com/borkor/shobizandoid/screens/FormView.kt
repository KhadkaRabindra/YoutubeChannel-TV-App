package com.borkor.shobizandoid.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.common.CommonInputField
import com.borkor.shobizandoid.common.ProgressBarComponent
import com.borkor.shobizandoid.data.model.YoutubeVideo
import com.borkor.shobizandoid.ui.theme.Pink40
import com.borkor.shobizandoid.ui.theme.inriaSansFamily
import com.borkor.shobizandoid.utils.DataStatus
import com.borkor.shobizandoid.utils.Resource

@Composable
fun FormView(appViewModel: VideosViewModel) {
    val addYoutubeVideoResponse by appViewModel.addYoutubeVideoResponse.collectAsState()

    AddYoutubeVideoScreen(appViewModel)

    when (addYoutubeVideoResponse.status) {
        DataStatus.SUCCESS -> {
            //AddYoutubeVideoScren(appViewModel)
        }

        DataStatus.ERROR -> {
            ErrorScreen()
        }

        DataStatus.LOADING -> {
            ProgressBarComponent()
        }

        DataStatus.NONE -> {

        }
    }
}

@Composable
fun AddYoutubeVideoScreen(appViewModel: VideosViewModel) {
    var videoID by remember { mutableStateOf("SNLBEj5MHTo") }
    var title by remember { mutableStateOf("RAP BASE") }
    var imageURL by remember { mutableStateOf("https://i.ytimg.com/vi/SNLBEj5MHTo/default.jpg") }

    val videoIDErrorMessage = remember { mutableStateOf("") }
    val titleErrorMessage = remember { mutableStateOf("") }
    val imageURLErrorMessage = remember { mutableStateOf("") }
    val context = LocalContext.current

    val addYoutubeVideoResponse by appViewModel.addYoutubeVideoResponse.collectAsState()

    when (addYoutubeVideoResponse.status) {
        DataStatus.SUCCESS -> {
            videoID = ""
            title = ""
            imageURL = ""
            appViewModel.changeStatusOfAddYoutubeVideoResponse(Resource.none())
        }

        DataStatus.ERROR -> {
            videoID = ""
            title = ""
            imageURL = ""
            appViewModel.changeStatusOfAddYoutubeVideoResponse(Resource.none())
        }

        DataStatus.LOADING -> {
        }

        DataStatus.NONE -> {
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CommonInputField(title = stringResource(id = R.string.video_id),
                    placeHolder = stringResource(id = R.string.please_provide_video_id),
                    value = videoID,
                    onTextChanged = {
                        videoID = it
                    },
                    content = {
                        if (videoIDErrorMessage.value.isNotEmpty()) {
                            Text(
                                fontFamily = inriaSansFamily,
                                fontWeight = FontWeight.Normal,
                                text = videoIDErrorMessage.value,
                                color = Pink40,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    })


                CommonInputField(title = stringResource(id = R.string.title),
                    placeHolder = stringResource(id = R.string.please_enter_title),
                    value = title,
                    onTextChanged = {
                        title = it
                    },
                    content = {
                        if (titleErrorMessage.value.isNotEmpty()) {
                            Text(
                                fontFamily = inriaSansFamily,
                                fontWeight = FontWeight.Normal,
                                text = titleErrorMessage.value,
                                color = Pink40,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    })


                CommonInputField(title = stringResource(id = R.string.image_url),
                    placeHolder = stringResource(id = R.string.please_enter_image_url),
                    value = imageURL,
                    onTextChanged = {
                        imageURL = it
                    },
                    content = {
                        if (imageURLErrorMessage.value.isNotEmpty()) {
                            Text(
                                fontFamily = inriaSansFamily,
                                fontWeight = FontWeight.Normal,
                                text = imageURLErrorMessage.value,
                                color = Pink40,
                                fontSize = 12.sp,
                                modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
                            )
                        }
                    })


                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp), onClick = {
                    videoIDErrorMessage.value = ""
                    titleErrorMessage.value = ""
                    imageURLErrorMessage.value = ""
                    val isValid = context.validateInput(
                        videoID = videoID,
                        videoIDErrorMessage = videoIDErrorMessage,
                        title = title,
                        titleErrorMessage = titleErrorMessage,
                        imageURL = imageURL,
                        imageURLErrorMessage = imageURLErrorMessage
                    )
                    if (isValid) {
                        appViewModel.addYouTubeVideo(
                            YoutubeVideo(
                                videoID = videoID,
                                title = title,
                                imageURL = imageURL,
                            )
                        )
                    }
                }) {
                    Text(text = "Validate")
                }
            }
        }
    }
}

private fun Context.validateInput(
    videoID: String,
    title: String,
    imageURL: String,
    videoIDErrorMessage: MutableState<String>,
    titleErrorMessage: MutableState<String>,
    imageURLErrorMessage: MutableState<String>,
): Boolean {
    var isValid = true

    if (videoID.isEmpty()) {
        videoIDErrorMessage.value = getString(R.string.please_provide_video_id)
        isValid = false
    }

    if (title.isEmpty()) {
        titleErrorMessage.value = getString(R.string.please_enter_title)
        isValid = false
    }

    if (imageURL.isEmpty()) {
        imageURLErrorMessage.value = getString(R.string.please_enter_image_url)
        isValid = false
    }

    return isValid
}