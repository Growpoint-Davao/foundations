package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.Routes
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

@Composable
fun SectionsContent(
    modifier: Modifier = Modifier,
    contentViewModel: ContentViewModel = hiltViewModel(),
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
    val navUIState = contentViewModel.navigationUIState.collectAsState()

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

    Column(modifier = modifier.fillMaxSize()) {
        if (navUIState.value.gettingStartedSelected) {
            GettingStarted(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                state = pageContentState
            )
            return
        }

        if (navUIState.value.salvationSelected) {
            Salvation(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = salvationViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.lordshipSelected) {
            Lordship(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = lordShipViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.identitySelected) {
            Identity(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = identityViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.powerSelected) {
            Power(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = powerViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.devotionSelected) {
            Devotion(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = devotionViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.churchSelected) {
            Church(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = churchViewModel,
                state = pageContentState
            )
            return
        }

        if (navUIState.value.discipleshipSelected) {
            Discipleship(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = discipleshipViewModel,
                state = pageContentState
            )
            return
        }
    }
}
