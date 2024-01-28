package com.borkor.shobizandoid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.borkor.shobizandoid.R
import com.borkor.shobizandoid.nav.AppNavigation
import com.borkor.shobizandoid.nav.Screens
import com.borkor.shobizandoid.nav.listOfNavItems
import com.borkor.shobizandoid.ui.theme.Dopely
import com.borkor.shobizandoid.ui.theme.Gray74
import com.borkor.shobizandoid.ui.theme.Gray86
import com.borkor.shobizandoid.ui.theme.inriaSansFamily
import com.borkor.shobizandoid.utils.openAppPlayStore
import kotlinx.coroutines.launch


@Composable
fun MainScreen(videoViewModel: VideosViewModel) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        ModalNavigationDrawer(
            {
                ModalDrawerSheet(drawerShape = RectangleShape) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Dopely)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(0.8f)
                                    .background(color = Dopely)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        color = Gray86,
                                        fontSize = 30.sp,
                                        text = stringResource(id = R.string.settings),
                                        fontFamily = inriaSansFamily,
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            LazyColumn {
                                items(listOfNavItems) {
                                    Row(
                                        modifier = Modifier
                                            .padding(start = 40.dp)
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = rememberRipple(color = Color.Blue)
                                            ) {
                                                if (it.route == Screens.SystemUpdateScreen.route) {
                                                    context.openAppPlayStore()
                                                } else {
                                                    navController.navigate(it.route)
                                                }
                                                scope.launch {
                                                    if (drawerState.isClosed)
                                                        drawerState.open()
                                                    else
                                                        drawerState.close()
                                                }

                                            },
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .width(40.dp)
                                                .height(40.dp),
                                            painter = painterResource(id = it.icon),
                                            contentDescription = stringResource(id = it.description),
                                        )

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(16.dp),
                                        ) {

                                            Text(
                                                color = Gray86,
                                                fontSize = 18.sp,
                                                text = stringResource(id = it.title),
                                                fontFamily = inriaSansFamily,
                                                fontWeight = FontWeight.Normal,
                                            )

                                            Text(
                                                color = Gray74,
                                                fontSize = 12.sp,
                                                text = stringResource(id = it.description),
                                                fontFamily = inriaSansFamily,
                                                fontWeight = FontWeight.Normal,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }, drawerState = drawerState
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                AppNavigation(
                    navController = navController,
                    drawerState = drawerState,
                    videoViewModel
                )
            }
        }
    }
}
