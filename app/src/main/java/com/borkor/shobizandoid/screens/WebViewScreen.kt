package com.borkor.shobizandoid.screens

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.borkor.shobizandoid.ui.theme.ShowBizTheme
import com.borkor.shobizandoid.utils.Urls

@Composable
fun WebViewScreen() {
    var webPageTitle by remember { mutableStateOf("") }
    val webViewState = rememberWebViewState(url = Urls.showbizTV)
    val chromeClient = object : AccompanistWebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            webPageTitle = title ?: ""
            super.onReceivedTitle(view, title)
        }
    }

    WebView(
        state = webViewState,
        onCreated = { it.settings.javaScriptEnabled = true },
        chromeClient = chromeClient,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun WebViewScreenPreview() {
    ShowBizTheme {
        WebViewScreen()
    }
}