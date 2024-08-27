package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.core.layout.WindowWidthSizeClass
import church.thegrowpoint.foundations.R
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
    discipleshipViewModel: DiscipleshipViewModel = hiltViewModel()
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
                modifier = Modifier.padding(horizontal = horizontalPadding)
            )
            return
        }

        if (navUIState.value.salvationSelected) {
            Salvation(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = salvationViewModel
            )
            return
        }

        if (navUIState.value.lordshipSelected) {
            Lordship(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = lordShipViewModel
            )
            return
        }

        if (navUIState.value.identitySelected) {
            Identity(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = identityViewModel
            )
            return
        }

        if (navUIState.value.powerSelected) {
            Power(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = powerViewModel
            )
            return
        }

        if (navUIState.value.devotionSelected) {
            Devotion(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = devotionViewModel
            )
            return
        }

        if (navUIState.value.churchSelected) {
            Church(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = churchViewModel
            )
            return
        }

        if (navUIState.value.discipleshipSelected) {
            Discipleship(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                viewModel = discipleshipViewModel
            )
            return
        }
    }
}
