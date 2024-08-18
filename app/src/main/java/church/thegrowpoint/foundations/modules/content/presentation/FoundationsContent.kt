package church.thegrowpoint.foundations.modules.content.presentation

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
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.Routes
import church.thegrowpoint.foundations.modules.content.Sections
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
    // drawer must be initially closed
    val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationDrawerScope = rememberCoroutineScope()
    val navController = rememberNavController()

    // drawer selected status state
    val navUIState = contentViewModel.navigationUIState.collectAsState()

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
                        Sections.entries.forEach { section ->
                            val selected = when (section) {
                                Sections.GETTING_STARTED -> navUIState.value.gettingStartedSelected
                                Sections.SALVATION -> navUIState.value.salvationSelected
                                Sections.LORDSHIP -> navUIState.value.lordshipSelected
                                Sections.IDENTITY -> navUIState.value.identitySelected
                                Sections.POWER -> navUIState.value.powerSelected
                                Sections.DEVOTION -> navUIState.value.devotionSelected
                                Sections.CHURCH -> navUIState.value.churchSelected
                                Sections.DISCIPLESHIP -> navUIState.value.discipleshipSelected
                            }

                            val onSelected = when (section) {
                                Sections.GETTING_STARTED -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            gettingStartedSelected = true
                                        )
                                    }
                                }

                                Sections.SALVATION -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            salvationSelected = true
                                        )
                                    }
                                }

                                Sections.LORDSHIP -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            lordshipSelected = true
                                        )
                                    }
                                }

                                Sections.IDENTITY -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            identitySelected = true
                                        )
                                    }
                                }

                                Sections.POWER -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            powerSelected = true
                                        )
                                    }
                                }

                                Sections.DEVOTION -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            devotionSelected = true
                                        )
                                    }
                                }

                                Sections.CHURCH -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            churchSelected = true
                                        )
                                    }
                                }

                                Sections.DISCIPLESHIP -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            discipleshipSelected = true
                                        )
                                    }
                                }
                            }

                            NavigationDrawerItemWithProgress(
                                title = stringResource(section.title),
                                subTitle = stringResource(section.subTitle),
                                icon = painterResource(section.icon),
                                selected = selected,
                                baseRoute = section.baseRoute,
                                navController = navController,
                                navigationDrawerState = navigationDrawerState,
                                navigationDrawerScope = navigationDrawerScope,
                                onClick = onSelected
                            )
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
                    title = navUIState.value.sectionTitle,
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
                    SectionsContent(
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
                                        contentViewModel.setSectionTitle(nextSectionTitle)
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
