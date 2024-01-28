package com.borkor.shobizandoid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borkor.shobizandoid.BuildConfig
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.ui.theme.Gray81
import com.borkor.shobizandoid.ui.theme.ShowBizTheme
import com.borkor.shobizandoid.ui.theme.inriaSansFamily

@Composable
fun AboutScreen(onBackIconPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Row(modifier = Modifier.padding(10.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
            ) {

                Image(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .height(24.dp)
                        .wrapContentSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = Color.Blue)
                        ) {
                            onBackIconPressed()
                        },
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = stringResource(id = R.string.shzbiz_icon_description)
                )

                Spacer(modifier = Modifier.width(30.dp))

                Column(modifier = Modifier.padding(start = 0.dp)) {
                    Text(
                        modifier = Modifier,
                        color = Gray81,
                        fontSize = 30.sp,
                        text = stringResource(id = R.string.settings),
                        fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        color = Gray81,
                        fontSize = 14.sp,
                        text = stringResource(id = R.string.version) + " " + BuildConfig.VERSION_NAME,
                        fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 5.dp)
                    .fillMaxSize()
                    .weight(3f)
            ) {
                Text(
                    modifier = Modifier,
                    color = Gray81,
                    fontSize = 24.sp,
                    text = stringResource(id = R.string.about),
                    fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Image(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxSize()
                        .clickable {
                            //navigateToWebView()
                        },
                    painter = painterResource(id = R.drawable.about_image),
                    contentDescription = stringResource(id = R.string.about_image_description)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxSize(),
                    color = Gray81,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_details),
                    fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ShowBizTheme {
        AboutScreen {

        }
    }
}