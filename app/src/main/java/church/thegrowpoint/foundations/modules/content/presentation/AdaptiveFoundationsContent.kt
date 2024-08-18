package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.Sections
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ChurchViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.composables.AnimatedNavigationFloatingActionButtons
import kotlinx.coroutines.launch

/**
 * This version of foundations content is only for non-phone screens where
 * adaptive navigation works really well.
 */
@Composable
fun AdaptiveFoundationsContent(
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
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val navSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    val navController = rememberNavController()

    // drawer selected status state
    val navUIState = contentViewModel.navigationUIState.collectAsState()

    NavigationSuiteScaffold(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_content_expanded)),
        layoutType = navSuiteType,
        navigationSuiteItems = {
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

                item(
                    icon = {
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(section.icon),
                            contentDescription = stringResource(section.title)
                        )
                    },
                    label = { Text(stringResource(section.title)) },
                    selected = selected,
                    onClick = {
                        val currentParentRoute = navController.currentDestination?.parent?.route
                        if (currentParentRoute != section.baseRoute) {
                            navController.navigate(section.baseRoute) {
                                if (currentParentRoute != null) {
                                    popUpTo(route = currentParentRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        onSelected()
                    }
                )
            }
        }
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pageLazyListState = rememberLazyListState()

        // determine the visibility of the floating navigation buttons based on the lazy list state of the page
        val navFabVisibility by remember {
            derivedStateOf {
                pageLazyListState.canScrollBackward || (!pageLazyListState.canScrollBackward && !pageLazyListState.canScrollForward)
            }
        }

        var previousButtonVisible by rememberSaveable {
            mutableStateOf(false)
        }

        Scaffold(
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
        ) { innerPadding ->
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
        }
    }
}
