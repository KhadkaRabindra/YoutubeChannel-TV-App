package com.tastyworks.app.scenes.home

/*
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.test.core.app.ActivityScenario.launch
import com.tastyworks.app.compose.colors.AppTheme.appColors
import com.tastyworks.app.scenes.home.HomeViewUtil.navigationFont
import com.tastyworks.app.scenes.home.HomeViewUtil.navigationIconColor
import com.tastyworks.app.scenes.home.HomeViewUtil.navigationTextColor
import com.tastyworks.app.scenes.positions.manage.ManagePositionsView
import com.tastyworks.app.scenes.search.SearchFragment
import com.tastyworks.app.scenes.sidemenu.SideMenuFragment
import com.tastyworks.app.scenes.underlyingQuoteDetails.UnderlyingQuoteDetailsFragment
import com.tastyworks.app.view.compose.FragmentWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val LANDSCAPE_SIDE_MENU_WIDTH = 0.2f
private const val TEXT_RESIZE_FACTOR = 0.9f
private val NAV_ITEM_FONT_SIZE = 10.sp

@Composable
internal fun HomeView(
    viewModel: HomeViewModel,
    fragmentManager: FragmentManager,
) {
    val rootScaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val landscapeMenuDrawerState = rememberDrawerState(DrawerValue.Closed)


    SyncDrawerStateWithViewModel(rootScaffoldState.drawerState, viewModel.isSideMenuVisibleState)
    SyncDrawerStateWithViewModel(
        landscapeMenuDrawerState,
        viewModel.isLandscapeSideMenuVisibleState,
    )

    // Trigger re-render, menu peeks when menu is not open in landscape
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration.orientation) {
        rootScaffoldState.drawerState.close()
        landscapeMenuDrawerState.close()
    }

    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val scope = rememberCoroutineScope()
    var offset2 by remember { mutableStateOf(0f) }


    Scaffold(
        */
/*modifier = Modifier.pointerInput(Unit) {
            detectTransformGestures { centroid, pan, zoom, rotation ->
                offset2 += pan.x
                if (offset2 < 0) offset2 = 0f
                if (offset2 > 300) offset2 = 300f // Ad

                scope.launch {
                    if (pan.x < 0) {
                        rootScaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    } else if (pan.x > 50) {
                        rootScaffoldState.drawerState.snapTo(DrawerValue.Open)
                    }
                    *//*
*/
/*awaitPointerEventScope {
                        pan.consumePositionChange()
                    }*//*
*/
/*
                }

            }
        },*//*

        scaffoldState = rootScaffoldState,
        backgroundColor = Color.Transparent,
        drawerGesturesEnabled = true,
        drawerContent = {
            FragmentWrapper(
                fragment = SideMenuFragment(),
                fragmentManager = fragmentManager,
                modifier = Modifier.fillMaxSize(),
            )
        },
        content = { padding ->
            LandscapeNavigationMenuDrawer(
                viewModel = viewModel,
                drawerState = landscapeMenuDrawerState,
                rootScaffoldState = rootScaffoldState.drawerState,
                scope= scope,
            ) {
                HomeContentView(
                    viewModel,
                    fragmentManager,
                    Modifier.padding(padding),
                )

                if (viewModel.isSymbolSearchVisibleState.value) {
                    FragmentWrapper(
                        fragment = SearchFragment(),
                        fragmentManager = fragmentManager,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                if (viewModel.isUnderlyingQuoteDetailsVisibleState.value) {
                    FragmentWrapper(
                        fragment =
                        UnderlyingQuoteDetailsFragment.newInstance(
                            viewModel.underlyingQuoteDetailsBundle,
                        ),
                        fragmentManager = fragmentManager,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                val managePositionsViewModel = viewModel.managePositionsViewModel
                if (managePositionsViewModel.isVisible.value) {
                    ManagePositionsView(
                        viewModel = managePositionsViewModel,
                        Modifier.padding(padding),
                    )
                }

                if (viewModel.isItemConfigurationModalVisible.value && isPortrait) {
                    HomeBottomBarConfigurationSheet(viewModel)
                }
            }
        },
        bottomBar = {
            if (isPortrait &&
                !viewModel.isSymbolSearchVisibleState.value &&
                !viewModel.isUnderlyingQuoteDetailsVisibleState.value
            ) {
                HomeBottomNavigationBar(viewModel)
            }
        },
    )
}

@Composable
fun HomeBottomNavigationBar(homeViewModel: HomeViewModel) {
    Column {
        Divider(color = appColors.colorBorderDividerHorizontal, thickness = 1.dp)
        BottomNavigation(
            Modifier.padding(top = 2.5.dp),
            backgroundColor = appColors.colorBackgroundGeneralPrimaryBackground,
        ) {
            homeViewModel.navigationMenuViewModel.currentNavigationItems.forEach { item ->
                HomeBottomNavigationItem(
                    item = item,
                    isSelected = homeViewModel.selectedNavigationItem == item,
                    onClick = {
                        homeViewModel.selectedNavigationItem = item
                    },
                    contentDescriptionId = item.contentDescriptionId,
                )
            }
        }
    }
}

@Composable
fun RowScope.HomeBottomNavigationItem(
    item: HomeNavigationItem,
    isSelected: Boolean,
    onClick: (HomeNavigationItem) -> Unit,
    @StringRes contentDescriptionId: Int,
) {
    val contentDescription = stringResource(id = contentDescriptionId)
    BottomNavigationItem(
        modifier =
        Modifier.semantics {
            this.contentDescription = contentDescription
        },
        icon = {
            Icon(
                painterResource(id = item.icon),
                contentDescription = null,
                tint = appColors.navigationIconColor(isSelected),
            )
        },
        label = {
            BoxWithConstraints {
                HomeNavigationItemText(
                    item,
                    isSelected,
                    color = appColors.navigationTextColor(isSelected),
                    modifier =
                    Modifier
                        .wrapContentWidth(unbounded = true)
                        .requiredWidth(maxWidth + 18.dp),
                )
            }
        },
        selected = isSelected,
        onClick = { onClick(item) },
    )
}

@Composable
fun HomeNavigationItemText(
    item: HomeNavigationItem,
    isSelected: Boolean,
    color: Color,
    modifier: Modifier = Modifier,
) {
    var fontSize by remember { mutableStateOf(NAV_ITEM_FONT_SIZE) }
    Text(
        text = stringResource(id = item.title).uppercase(),
        fontFamily = navigationFont(isSelected),
        fontSize = fontSize,
        color = color,
        softWrap = false,
        modifier = modifier,
        onTextLayout = { layoutResult ->
            if (layoutResult.didOverflowWidth) {
                fontSize = fontSize.times(TEXT_RESIZE_FACTOR)
            }
        },
    )
}

@Composable
private fun LandscapeNavigationMenuDrawer(
    viewModel: HomeViewModel,
    drawerState: DrawerState,
    rootScaffoldState : DrawerState,
    scope : CoroutineScope,
    content: @Composable () -> Unit,
) {
    var offset1 by remember { mutableStateOf(0f) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            modifier = Modifier.pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    offset1 += pan.x
                    if (offset1 < 0) offset1 = 0f
                    if (offset1 > 300) offset1 = 300f // Adjust as needed

                    scope.launch {
                        if (pan.x < 0) {
                            rootScaffoldState.snapTo(DrawerValue.Closed)
                        } else if (pan.x > 50) {
                            rootScaffoldState.snapTo(DrawerValue.Open)
                        }
                        */
/*awaitPointerEventScope {
                            pan.consumePositionChange()
                        }*//*

                    }

                }
            },
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerBackgroundColor = Color.Transparent,
            // Remove shadow due to custom MenuColumn width
            drawerElevation = 0.dp,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    LandscapeNavigationSideMenuColumn(viewModel = viewModel)
                }
            },
            content = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    content()
                }
            },
        )
    }
}

@Composable
private fun LandscapeNavigationSideMenuColumn(viewModel: HomeViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier =
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(LANDSCAPE_SIDE_MENU_WIDTH)
            .background(
                appColors.colorBackgroundGeneralSecondarySurface,
            ),
    ) {
        HomeNavigationItem.values().forEach { item ->
            if (item != HomeNavigationItem.MORE) {
                val isSelected = viewModel.selectedNavigationItem == item
                LandscapeNavigationSideMenuItem(item, isSelected) {
                    viewModel.selectedNavigationItem = item
                }
            }
        }
    }
}

@Composable
fun LandscapeNavigationSideMenuItem(
    item: HomeNavigationItem,
    isSelected: Boolean,
    onClick: (HomeNavigationItem) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(item) },
    ) {
        Icon(
            painterResource(id = item.icon),
            contentDescription = null,
            tint = appColors.navigationIconColor(isSelected),
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(id = item.title).uppercase(),
            fontFamily = navigationFont(isSelected),
            fontSize = 10.sp,
            color = appColors.navigationTextColor(isSelected),
            softWrap = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SyncDrawerStateWithViewModel(
    drawerState: DrawerState,
    stateVariable: MutableState<Boolean>,
) {
    LaunchedEffect(stateVariable.value) {
        if (stateVariable.value) drawerState.open() else drawerState.close()
    }
    LaunchedEffect(drawerState.isOpen) {
        stateVariable.value = drawerState.isOpen
    }
}
*/

