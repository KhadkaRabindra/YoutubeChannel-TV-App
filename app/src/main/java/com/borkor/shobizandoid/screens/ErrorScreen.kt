package com.borkor.shobizandoid.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.ui.theme.Gray86
import com.borkor.shobizandoid.ui.theme.inriaSansFamily

@Composable
fun ErrorScreen() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp),
                color = Gray86,
                fontSize = 15.sp,
                text = stringResource(id = R.string.error_message),
                fontFamily = inriaSansFamily, fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}