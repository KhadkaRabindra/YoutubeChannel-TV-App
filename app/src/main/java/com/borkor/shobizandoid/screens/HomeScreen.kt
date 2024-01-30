package com.borkor.shobizandoid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.ui.theme.Gray93
import com.borkor.shobizandoid.ui.theme.ShowBizTheme
import com.borkor.shobizandoid.ui.theme.inriaSansFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    drawerState: DrawerState, videoViewModel: VideosViewModel, navigateToWebView: () -> Unit,
    navigateToYoutubeView: (string: String) -> Unit
) {
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            topBar = {
                TopAppBar {
                    scope.launch {
                        if (drawerState.isClosed)
                            drawerState.open()
                        else
                            drawerState.close()
                    }
                }
            }
        ) { innerPadding ->
            Box {
                Column(
                    modifier = Modifier
                        .background(Gray93)
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 30.dp, end = 30.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(color = Color.Blue)
                                ) {
                                    navigateToWebView()
                                },
                            painter = painterResource(id = R.drawable.webview_banner),
                            contentDescription = stringResource(id = R.string.shzbiz_icon_description),
                        )
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp),
                            color = Color.Black,
                            fontSize = 24.sp,
                            text = stringResource(id = R.string.videos_and_more),
                            fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        MoreVideosScreen(videoViewModel, navigateToYoutubeView)
                    }
                }

                InterstitialAdsScreen(LocalContext.current, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}


@Composable
fun TopAppBar(onSettingIconClick: () -> Unit) {
    var formattedTime by remember { mutableStateOf("") }
    val sdf = remember { SimpleDateFormat("HH:mm", Locale.ROOT) }
    LaunchedEffect(key1 = Unit) {
        while (isActive) {
            formattedTime = sdf.format(Date())
            delay(60 * 1000)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray93),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(67.dp)
                .height(50.dp)
                .padding(start = 10.dp, end = 5.dp),
            painter = painterResource(id = R.drawable.shdbiz_icon),
            contentDescription = stringResource(id = R.string.shzbiz_icon_description)
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            color = Color.Black,
            fontSize = 24.sp,
            text = stringResource(id = R.string.homepage),
            fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Image(
            modifier = Modifier
                .width(37.dp)
                .height(37.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.Blue)
                ) {
                    onSettingIconClick()
                }
                .padding(start = 10.dp, end = 10.dp),
            painter = painterResource(id = R.drawable.settings),
            contentDescription = stringResource(id = R.string.shzbiz_icon_description),
        )

        Text(
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp),
            color = Color.Black,
            fontSize = 20.sp,
            text = formattedTime,
            fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    ShowBizTheme {
        TopAppBar {

        }
    }
}