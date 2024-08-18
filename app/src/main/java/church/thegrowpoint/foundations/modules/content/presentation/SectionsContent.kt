package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.Routes
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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel

@Composable
fun SectionsContent(
    modifier: Modifier = Modifier,
    salvationViewModel: SalvationViewModel = hiltViewModel(),
    lordShipViewModel: LordshipViewModel = hiltViewModel(),
    identityViewModel: IdentityViewModel = hiltViewModel(),
    powerViewModel: PowerViewModel = hiltViewModel(),
    devotionViewModel: DevotionViewModel = hiltViewModel(),
    churchViewModel: ChurchViewModel = hiltViewModel(),
    discipleshipViewModel: DiscipleshipViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    pageContentState: LazyListState = rememberLazyListState(),
    currentDestination: String = Routes.GETTING_STARTED.route
) {
    // get the orientation of the device
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val horizontalPadding = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.MEDIUM -> {
            dimensionResource(R.dimen.padding_content_horizontal_medium)
        }

        WindowWidthSizeClass.EXPANDED -> {
            dimensionResource(R.dimen.padding_content_horizontal_expanded)
        }

        else -> {
            dimensionResource(R.dimen.padding_content_horizontal_compact)
        }
    }

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = currentDestination,
        enterTransition = { fadeIn() + slideInHorizontally() },
        exitTransition = { slideOutHorizontally() + fadeOut() }
    ) {
        navigation(
            startDestination = "${Routes.GETTING_STARTED.route}/1",
            route = currentDestination
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