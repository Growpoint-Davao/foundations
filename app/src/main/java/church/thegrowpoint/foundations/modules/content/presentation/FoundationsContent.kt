package church.thegrowpoint.foundations.modules.content.presentation

import android.content.res.Configuration
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church1
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church2
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church3
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church4
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church5
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion1
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion2
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion3
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion4
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion5
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion6
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion7
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship1
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship2
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship3
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship4
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship5
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship6
import church.thegrowpoint.foundations.modules.content.presentation.pages.gettingStarted.GettingStarted1
import church.thegrowpoint.foundations.modules.content.presentation.pages.gettingStarted.GettingStarted2
import church.thegrowpoint.foundations.modules.content.presentation.pages.gettingStarted.GettingStarted3
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity1
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity2
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity3
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity4
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity5
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity6
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity7
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship1
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship2
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship3
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship4
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship5
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship6
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship7
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power1
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power2
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power3
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power4
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power5
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power6
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power7
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation1
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation10
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation2
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation3
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation4
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation5
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation6
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation7
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation8
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation9
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ChurchViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.composables.AnimatedNavigationFloatingActionButtons
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.composables.GrowpointTitlePanel
import church.thegrowpoint.foundations.ui.composables.NavigationDrawerItemWithProgress
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundationsContent(
    contentViewModel: ContentViewModel,
    authViewModel: AuthViewModel,
    salvationViewModel: SalvationViewModel = hiltViewModel(),
    lordShipViewModel: LordshipViewModel = hiltViewModel(),
    identityViewModel: IdentityViewModel = hiltViewModel(),
    powerViewModel: PowerViewModel = hiltViewModel(),
    devotionViewModel: DevotionViewModel = hiltViewModel(),
    churchViewModel: ChurchViewModel = hiltViewModel(),
    discipleshipViewModel: DiscipleshipViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    // set initial title
    var topBarTitle by rememberSaveable {
        mutableStateOf(context.getString(R.string.getting_started))
    }

    // drawer must be initially closed
    val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationDrawerScope = rememberCoroutineScope()
    val navController = rememberNavController()

    // drawer selected status state
    val navDrawerItemsUIState = contentViewModel.navigationDrawerItemsUIState.collectAsState()

    ModalNavigationDrawer(
        drawerContent = {
            // make sure we display correct logo for light and dark mode
            var logoPainterResource = painterResource(R.drawable.gp_login_logo)
            if (!isSystemInDarkTheme()) {
                logoPainterResource = painterResource(R.drawable.gp_login_logo_light)
            }

            ModalDrawerSheet {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    GrowpointTitlePanel(
                        modifier = Modifier.padding(16.dp),
                        title = stringResource(R.string.foundations),
                        subTitle = stringResource(R.string.established_for_growing),
                        logo = logoPainterResource
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                HorizontalDivider()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState()
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.getting_started),
                            subTitle = stringResource(R.string.introduction),
                            icon = painterResource(R.drawable.getting_started),
                            selected = navDrawerItemsUIState.value.gettingStartedSelected,
                            baseRoute = Routes.GETTING_STARTED.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.getting_started)
                            contentViewModel.setNavigationDrawerItemSelected(gettingStartedSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.salvation),
                            subTitle = stringResource(R.string.lesson_1),
                            icon = painterResource(R.drawable.salvation),
                            selected = navDrawerItemsUIState.value.salvationSelected,
                            baseRoute = Routes.SALVATION.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.salvation)
                            contentViewModel.setNavigationDrawerItemSelected(salvationSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.lordship),
                            subTitle = stringResource(R.string.lesson_2),
                            icon = painterResource(R.drawable.lordship),
                            selected = navDrawerItemsUIState.value.lordshipSelected,
                            baseRoute = Routes.LORDSHIP.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.lordship)
                            contentViewModel.setNavigationDrawerItemSelected(lordshipSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.identity),
                            subTitle = stringResource(R.string.lesson_3),
                            icon = painterResource(R.drawable.identity),
                            selected = navDrawerItemsUIState.value.identitySelected,
                            baseRoute = Routes.IDENTITY.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.identity)
                            contentViewModel.setNavigationDrawerItemSelected(identitySelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.power),
                            subTitle = stringResource(R.string.lesson_4),
                            icon = painterResource(R.drawable.power),
                            selected = navDrawerItemsUIState.value.powerSelected,
                            baseRoute = Routes.POWER.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.power)
                            contentViewModel.setNavigationDrawerItemSelected(powerSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.devotion),
                            subTitle = stringResource(R.string.lesson_5),
                            icon = painterResource(R.drawable.devotion),
                            selected = navDrawerItemsUIState.value.devotionSelected,
                            baseRoute = Routes.DEVOTION.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.devotion)
                            contentViewModel.setNavigationDrawerItemSelected(devotionSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.church),
                            subTitle = stringResource(R.string.lesson_6),
                            icon = painterResource(R.drawable.church),
                            selected = navDrawerItemsUIState.value.churchSelected,
                            baseRoute = Routes.CHURCH.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.church)
                            contentViewModel.setNavigationDrawerItemSelected(churchSelected = true)
                        }
                        NavigationDrawerItemWithProgress(
                            title = stringResource(R.string.discipleship),
                            subTitle = stringResource(R.string.lesson_7),
                            icon = painterResource(R.drawable.discipleship),
                            selected = navDrawerItemsUIState.value.discipleshipSelected,
                            baseRoute = Routes.DISCIPLESHIP.route,
                            navController = navController,
                            navigationDrawerState = navigationDrawerState,
                            navigationDrawerScope = navigationDrawerScope
                        ) {
                            topBarTitle = context.getString(R.string.discipleship)
                            contentViewModel.setNavigationDrawerItemSelected(discipleshipSelected = true)
                        }

                        // other drawer items
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))
                        NavigationDrawerItem(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            label = {
                                Text(
                                    text = stringResource(R.string.logout),
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = stringResource(R.string.logout)
                                )
                            },
                            selected = false,
                            onClick = {
                                authViewModel.logout()
                            }
                        )
                    }
                }
            }
        },
        drawerState = navigationDrawerState
    ) {
        // keyboard controller for hiding the keyboard
        val keyboardController = LocalSoftwareKeyboardController.current

        val coroutineScope = rememberCoroutineScope()
        val pageLazyListState = rememberLazyListState()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        // determine the visibility of the floating navigation buttons based on the lazy list state of the page
        val navFabVisibility by remember {
            derivedStateOf {
                pageLazyListState.canScrollBackward || (!pageLazyListState.canScrollBackward && !pageLazyListState.canScrollForward)
            }
        }

        var previousButtonVisible by rememberSaveable {
            mutableStateOf(false)
        }

        LaunchedEffect(navFabVisibility) {
            val currentDestination = navController.currentDestination
            val currentRoute = currentDestination?.route
            val segments = currentRoute?.split('/')
            val currentPage = segments?.get(1)?.toInt() ?: 0
            if (currentPage == 1) {
                // hide the previous button
                previousButtonVisible = false
            } else {
                previousButtonVisible = true
            }
        }

        Scaffold(
            topBar = {
                CenteredTopAppBar(
                    title = topBarTitle,
                    scrollBehavior = scrollBehavior,
                    navIconContentDescription = stringResource(R.string.toggle_navigation_drawer)
                ) {
                    // toggle the navigation drawer
                    navigationDrawerScope.launch {
                        keyboardController?.hide()

                        navigationDrawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Content(
                        navController = navController,
                        salvationViewModel = salvationViewModel,
                        lordShipViewModel = lordShipViewModel,
                        identityViewModel = identityViewModel,
                        powerViewModel = powerViewModel,
                        devotionViewModel = devotionViewModel,
                        churchViewModel = churchViewModel,
                        discipleshipViewModel = discipleshipViewModel,
                        pageContentState = pageLazyListState
                    )
                }
            },
            floatingActionButton = {
                AnimatedNavigationFloatingActionButtons(
                    isVisible = navFabVisibility,
                    previousButtonVisible = previousButtonVisible,
                    onPreviousClick = {
                        val currentDestination = navController.currentDestination
                        val currentRoute = currentDestination?.route
                        val segments = currentRoute?.split('/')
                        val currentPage = segments?.get(1)?.toInt() ?: 0
                        if (currentPage > 1) {
                            // only pop if not in page 1
                            navController.popBackStack()
                        }

                        // scroll back to top
                        coroutineScope.launch {
                            pageLazyListState.animateScrollToItem(index = 0) // Smoothly animates to the top
                        }
                    },
                    onNextClick = {
                        val currentDestination = navController.currentDestination
                        val currentRoute = currentDestination?.route
                        val segments = currentRoute?.split('/')
                        val section = segments?.get(0)
                        val nextPage = segments?.get(1)?.toInt()?.plus(1) ?: 1

                        if (section != null) {
                            val sectionPageCount = contentViewModel.getSectionPageCount(section)

                            if (nextPage > sectionPageCount) {
                                val nextSection = contentViewModel.getNextSection(section)
                                if (nextSection != null) {
                                    val nextSectionTitle =
                                        contentViewModel.getTitleResource(nextSection)
                                    if (nextSectionTitle != null) {
                                        topBarTitle = nextSectionTitle
                                        contentViewModel.setNavigationDrawerItemSelected(nextSection)
                                    }

                                    try {
                                        navController.navigate(nextSection) {
                                            popUpTo(route = section) {
                                                inclusive = true
                                            }
                                        }
                                    } catch (e: IllegalArgumentException) {
                                        // probably no more section
                                        e.printStackTrace()
                                    }

                                    previousButtonVisible = false
                                }
                            } else {
                                navController.navigate("$section/$nextPage")
                                previousButtonVisible = true
                            }
                        }

                        // scroll back to top
                        coroutineScope.launch {
                            pageLazyListState.animateScrollToItem(index = 0) // Smoothly animates to the top
                        }
                    }
                )
            }
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    salvationViewModel: SalvationViewModel = hiltViewModel(),
    lordShipViewModel: LordshipViewModel = hiltViewModel(),
    identityViewModel: IdentityViewModel = hiltViewModel(),
    powerViewModel: PowerViewModel = hiltViewModel(),
    devotionViewModel: DevotionViewModel = hiltViewModel(),
    churchViewModel: ChurchViewModel = hiltViewModel(),
    discipleshipViewModel: DiscipleshipViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    pageContentState: LazyListState = rememberLazyListState()
) {
    // TODO: resolve start destination
    val initialSectionDestination = Routes.GETTING_STARTED.route

    // get the orientation of the device
    val orientation = LocalConfiguration.current.orientation
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val horizontalPadding = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.MEDIUM -> {
            dimensionResource(R.dimen.padding_content_horizontal_medium)
        }

        WindowWidthSizeClass.EXPANDED -> {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    dimensionResource(R.dimen.padding_content_horizontal_medium)
                }

                Configuration.ORIENTATION_PORTRAIT -> {
                    dimensionResource(R.dimen.padding_content_horizontal_expanded)
                }

                else -> dimensionResource(R.dimen.padding_content_horizontal_medium)
            }
        }

        else -> {
            dimensionResource(R.dimen.padding_content_horizontal_compact)
        }
    }

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = initialSectionDestination,
        enterTransition = { fadeIn() + slideInHorizontally() },
        exitTransition = { slideOutHorizontally() + fadeOut() }
    ) {
        navigation(
            startDestination = "${Routes.GETTING_STARTED.route}/1",
            route = initialSectionDestination
        ) {
            composable(route = "${Routes.GETTING_STARTED.route}/1") {
                GettingStarted1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
            composable(route = "${Routes.GETTING_STARTED.route}/2") {
                GettingStarted2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
            composable(route = "${Routes.GETTING_STARTED.route}/3") {
                GettingStarted3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.SALVATION.route}/1",
            route = Routes.SALVATION.route
        ) {
            composable(route = "${Routes.SALVATION.route}/1") {
                Salvation1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/2") {
                Salvation2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/3") {
                Salvation3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/4") {
                Salvation4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/5") {
                Salvation5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/6") {
                Salvation6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/7") {
                Salvation7(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/8") {
                Salvation8(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = salvationViewModel
                )
            }

            composable(route = "${Routes.SALVATION.route}/9") {
                Salvation9(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }

            composable(route = "${Routes.SALVATION.route}/10") {
                Salvation10(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.LORDSHIP.route}/1",
            route = Routes.LORDSHIP.route
        ) {
            composable(route = "${Routes.LORDSHIP.route}/1") {
                Lordship1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/2") {
                Lordship2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/3") {
                Lordship3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/4") {
                Lordship4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/5") {
                Lordship5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/6") {
                Lordship6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }

            composable(route = "${Routes.LORDSHIP.route}/7") {
                Lordship7(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = lordShipViewModel
                )
            }
        }

        navigation(
            startDestination = "${Routes.IDENTITY.route}/1",
            route = Routes.IDENTITY.route
        ) {
            composable(route = "${Routes.IDENTITY.route}/1") {
                Identity1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }

            composable(route = "${Routes.IDENTITY.route}/2") {
                Identity2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = identityViewModel
                )
            }

            composable(route = "${Routes.IDENTITY.route}/3") {
                Identity3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = identityViewModel
                )
            }

            composable(route = "${Routes.IDENTITY.route}/4") {
                Identity4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = identityViewModel
                )
            }

            composable(route = "${Routes.IDENTITY.route}/5") {
                Identity5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = identityViewModel
                )
            }

            composable(route = "${Routes.IDENTITY.route}/6") {
                Identity6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = identityViewModel
                )
            }

            composable(route = "${Routes.IDENTITY.route}/7") {
                Identity7(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.POWER.route}/1",
            route = Routes.POWER.route
        ) {
            composable(route = "${Routes.POWER.route}/1") {
                Power1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/2") {
                Power2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/3") {
                Power3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/4") {
                Power4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/5") {
                Power5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/6") {
                Power6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = powerViewModel
                )
            }

            composable(route = "${Routes.POWER.route}/7") {
                Power7(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.DEVOTION.route}/1",
            route = Routes.DEVOTION.route
        ) {
            composable(route = "${Routes.DEVOTION.route}/1") {
                Devotion1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/2") {
                Devotion2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/3") {
                Devotion3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/4") {
                Devotion4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/5") {
                Devotion5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/6") {
                Devotion6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = devotionViewModel
                )
            }

            composable(route = "${Routes.DEVOTION.route}/7") {
                Devotion7(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.CHURCH.route}/1",
            route = Routes.CHURCH.route
        ) {
            composable(route = "${Routes.CHURCH.route}/1") {
                Church1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = churchViewModel
                )
            }

            composable(route = "${Routes.CHURCH.route}/2") {
                Church2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = churchViewModel
                )
            }

            composable(route = "${Routes.CHURCH.route}/3") {
                Church3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = churchViewModel
                )
            }

            composable(route = "${Routes.CHURCH.route}/4") {
                Church4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = churchViewModel
                )
            }

            composable(route = "${Routes.CHURCH.route}/5") {
                Church5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }

        navigation(
            startDestination = "${Routes.DISCIPLESHIP.route}/1",
            route = Routes.DISCIPLESHIP.route
        ) {
            composable(route = "${Routes.DISCIPLESHIP.route}/1") {
                Discipleship1(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = discipleshipViewModel
                )
            }

            composable(route = "${Routes.DISCIPLESHIP.route}/2") {
                Discipleship2(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }

            composable(route = "${Routes.DISCIPLESHIP.route}/3") {
                Discipleship3(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = discipleshipViewModel
                )
            }

            composable(route = "${Routes.DISCIPLESHIP.route}/4") {
                Discipleship4(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState,
                    viewModel = discipleshipViewModel
                )
            }

            composable(route = "${Routes.DISCIPLESHIP.route}/5") {
                Discipleship5(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }

            composable(route = "${Routes.DISCIPLESHIP.route}/6") {
                Discipleship6(
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    state = pageContentState
                )
            }
        }
    }
}
