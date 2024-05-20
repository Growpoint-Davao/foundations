package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.presentation.pages.GettingStartedPage1
import church.thegrowpoint.foundations.modules.content.presentation.pages.GettingStartedPage2
import church.thegrowpoint.foundations.modules.content.presentation.pages.GettingStartedPage3
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation1
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation10
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation2
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation3
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation4
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation5
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation6
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation7
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation8
import church.thegrowpoint.foundations.modules.content.presentation.pages.Salvation9
import church.thegrowpoint.foundations.ui.composables.AnimatedNavigationFloatingActionButtons
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.composables.GrowpointTitlePanel
import church.thegrowpoint.foundations.ui.composables.NavigationDrawerItemWithProgress
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundationsContent(
    authViewModel: AuthViewModel,
    contentViewModel: ContentViewModel,
    appNavController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
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
            ModalDrawerSheet {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    GrowpointTitlePanel(
                        modifier = Modifier.padding(16.dp),
                        title = stringResource(R.string.foundations),
                        subTitle = stringResource(R.string.established_for_growing),
                        logo = painterResource(R.drawable.gp_login_logo)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                HorizontalDivider()
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
            }
        },
        drawerState = navigationDrawerState
    ) {
        // val authState by authViewModel.authState.collectAsState()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        val pageContentState = rememberLazyListState()
        val navFabVisibility by remember {
            derivedStateOf {
                // show floating navigation buttons when the user is at the end of the list, or
                // if user could not scroll because all the content are visible
                pageContentState.canScrollBackward || (!pageContentState.canScrollBackward && !pageContentState.canScrollForward)
            }
        }

        Scaffold(
            topBar = {
                CenteredTopAppBar(
                    title = topBarTitle,
                    scrollBehavior = scrollBehavior
                ) {
                    // toggle the navigation drawer
                    navigationDrawerScope.launch {
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
                        contentViewModel = contentViewModel,
                        pageContentState = pageContentState
                    )
                }
            },
            floatingActionButton = {
                AnimatedNavigationFloatingActionButtons(
                    isVisible = navFabVisibility,
                    onPreviousClick = {
                        navController.popBackStack()
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
                                    val sectionTitle = contentViewModel.getTitleResource(nextSection)
                                    if (sectionTitle != null) {
                                        topBarTitle = sectionTitle
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
                                }
                            } else {
                                navController.navigate("$section/$nextPage")
                            }
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
    contentViewModel: ContentViewModel,
    navController: NavHostController = rememberNavController(),
    pageContentState: LazyListState = rememberLazyListState()
) {
    // TODO: resolve start destination
    val initialSectionDestination = Routes.GETTING_STARTED.route
    contentViewModel.setNavigationDrawerItemSelected(gettingStartedSelected = true)

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = initialSectionDestination
    ) {
        navigation(
            startDestination = "${Routes.GETTING_STARTED.route}/1",
            route = initialSectionDestination
        ) {
            composable(route = "${Routes.GETTING_STARTED.route}/1") {
                GettingStartedPage1(state = pageContentState)
            }
            composable(route = "${Routes.GETTING_STARTED.route}/2") {
                GettingStartedPage2(state = pageContentState)
            }
            composable(route = "${Routes.GETTING_STARTED.route}/3") {
                GettingStartedPage3(state = pageContentState)
            }
        }

        navigation(
            startDestination = "${Routes.SALVATION.route}/1",
            route = Routes.SALVATION.route
        ) {
            composable(route = "${Routes.SALVATION.route}/1") {
                Salvation1(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/2") {
                Salvation2(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/3") {
                Salvation3(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/4") {
                Salvation4(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/5") {
                Salvation5(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/6") {
                Salvation6(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/7") {
                Salvation7(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/8") {
                Salvation8(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/9") {
                Salvation9(state = pageContentState)
            }

            composable(route = "${Routes.SALVATION.route}/10") {
                Salvation10(state = pageContentState)
            }
        }
    }
}
