package church.thegrowpoint.foundations.dynamics.foundation.presentation.states

import church.thegrowpoint.foundations.dynamics.foundation.Routes

/**
 * # NavigationUIState
 *
 * @property selectedSectionRoute The selected section route.
 */
data class NavigationUIState(
    val selectedSectionRoute: String = Routes.GETTING_STARTED.route
)
