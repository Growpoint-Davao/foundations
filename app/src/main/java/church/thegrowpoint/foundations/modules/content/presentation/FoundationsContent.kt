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
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.composables.GrowpointTitlePanel
import church.thegrowpoint.foundations.ui.composables.NavigationDrawerItemWithProgress
import church.thegrowpoint.foundations.ui.composables.AnimatedNavigationFloatingActionButtons
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundationsContent(
    authViewModel: AuthViewModel,
    contentViewModel: ContentViewModel,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    var topBarTitle by rememberSaveable {
        mutableStateOf(context.getString(R.string.getting_started))
    }

    // drawer must be initially closed
    val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationDrawerScope = rememberCoroutineScope()

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
                    baseRoute = Routes.GETTING_STARTED.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.getting_started)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.salvation),
                    subTitle = stringResource(R.string.lesson_1),
                    icon = painterResource(R.drawable.salvation),
                    baseRoute = Routes.SALVATION.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.salvation)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.lordship),
                    subTitle = stringResource(R.string.lesson_2),
                    icon = painterResource(R.drawable.lordship),
                    baseRoute = Routes.LORDSHIP.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.lordship)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.identity),
                    subTitle = stringResource(R.string.lesson_3),
                    icon = painterResource(R.drawable.identity),
                    baseRoute = Routes.IDENTITY.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.identity)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.power),
                    subTitle = stringResource(R.string.lesson_4),
                    icon = painterResource(R.drawable.power),
                    baseRoute = Routes.POWER.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.power)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.devotion),
                    subTitle = stringResource(R.string.lesson_5),
                    icon = painterResource(R.drawable.devotion),
                    baseRoute = Routes.DEVOTION.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.devotion)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.church),
                    subTitle = stringResource(R.string.lesson_6),
                    icon = painterResource(R.drawable.church),
                    baseRoute = Routes.CHURCH.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.church)
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.discipleship),
                    subTitle = stringResource(R.string.lesson_7),
                    icon = painterResource(R.drawable.discipleship),
                    baseRoute = Routes.DISCIPLESHIP.route,
                    navController = navController,
                    navigationDrawerState = navigationDrawerState,
                    navigationDrawerScope = navigationDrawerScope
                ) {
                    topBarTitle = context.getString(R.string.discipleship)
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
                pageContentState.canScrollBackward
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

                    },
                    onNextClick = {

                    }
                )
            },
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
    val startingSection = Routes.GETTING_STARTED.route

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startingSection,
        route = Routes.CONTENT.name
    ) {
        val gettingStartedPages = contentViewModel.getGettingStarted()
        if (gettingStartedPages != null) {
            navigation(
                startDestination = "0",
                route = Routes.GETTING_STARTED.route
            ) {
                for ((page, pageContents) in gettingStartedPages.withIndex()) {
                    composable(route = "${page}") {
                        PageContent(
                            items = pageContents,
                            modifier = Modifier.padding(all = 8.dp),
                            state = pageContentState
                        )
                    }
                }
            }
        }

        val salvationPages = contentViewModel.getSalvation()
        if (salvationPages != null) {
            navigation(
                startDestination = "0",
                route = Routes.SALVATION.route
            ) {
                for ((page, pageContents) in salvationPages.withIndex()) {
                    composable(route = "${page}") {
                        PageContent(
                            items = pageContents,
                            modifier = Modifier.padding(all = 8.dp),
                            state = pageContentState
                        )
                    }
                }
            }
        }
    }
}
