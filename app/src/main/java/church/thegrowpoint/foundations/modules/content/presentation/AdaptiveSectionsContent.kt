package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.Routes
import church.thegrowpoint.foundations.modules.content.Sections
import church.thegrowpoint.foundations.modules.content.presentation.pages.church.Church
import church.thegrowpoint.foundations.modules.content.presentation.pages.devotion.Devotion
import church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship.Discipleship
import church.thegrowpoint.foundations.modules.content.presentation.pages.gettingStarted.GettingStarted
import church.thegrowpoint.foundations.modules.content.presentation.pages.identity.Identity
import church.thegrowpoint.foundations.modules.content.presentation.pages.lordship.Lordship
import church.thegrowpoint.foundations.modules.content.presentation.pages.power.Power
import church.thegrowpoint.foundations.modules.content.presentation.pages.salvation.Salvation
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ChurchViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.composables.EmphasisLabel

@Composable
fun AdaptiveSectionsContent(
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
    val windowSizeClass = adaptiveInfo.windowSizeClass

    // nav suite items padding
    var navSuiteItemPaddingVertical = 0.dp

    var navSuiteType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
        navSuiteType = NavigationSuiteType.NavigationDrawer
        navSuiteItemPaddingVertical = dimensionResource(R.dimen.padding_vertical) // add padding when expanded
    }

    // nav ui state which will control what section is active
    val navUIState = contentViewModel.navigationUIState.collectAsState()

    NavigationSuiteScaffold(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_content_expanded)),
        layoutType = navSuiteType,
        navigationSuiteItems = {
            Sections.entries.forEach { section ->
                item(
                    modifier = Modifier.padding(vertical = navSuiteItemPaddingVertical),
                    icon = {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(vertical = 4.dp),
                            painter = painterResource(section.icon),
                            contentDescription = stringResource(section.title)
                        )
                    },
                    label = {
                        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                            EmphasisLabel(
                                title = stringResource(section.title),
                                subTitle = stringResource(section.subTitle)
                            )
                        } else {
                            EmphasisLabel(
                                title = stringResource(section.title)
                            )
                        }
                    },
                    selected = section.baseRoute == navUIState.value.selectedSectionRoute,
                    onClick = {
                        contentViewModel.setSelectedSectionRoute(section.baseRoute)
                    }
                )
            }

            item(
                modifier = Modifier.padding(vertical = navSuiteItemPaddingVertical),
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(48.dp)
                            .padding(vertical = 4.dp),
                        imageVector = Icons.AutoMirrored.Rounded.Logout,
                        contentDescription = stringResource(R.string.logout),
                    )
                },
                label = {
                    EmphasisLabel(title = stringResource(R.string.logout))
                },
                selected = false,
                onClick = {
                    authViewModel.logout()
                }
            )
        }
    ) {
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

        Column(modifier = Modifier.padding(horizontal = horizontalPadding)) {
            when(navUIState.value.selectedSectionRoute) {
                Routes.GETTING_STARTED.route -> GettingStarted()
                Routes.SALVATION.route -> Salvation(viewModel = salvationViewModel)
                Routes.LORDSHIP.route -> Lordship(viewModel = lordShipViewModel)
                Routes.IDENTITY.route -> Identity(viewModel = identityViewModel)
                Routes.POWER.route -> Power(viewModel = powerViewModel)
                Routes.DEVOTION.route -> Devotion(viewModel = devotionViewModel)
                Routes.CHURCH.route -> Church(viewModel = churchViewModel)
                else -> Discipleship(viewModel = discipleshipViewModel)
            }
        }
    }
}
