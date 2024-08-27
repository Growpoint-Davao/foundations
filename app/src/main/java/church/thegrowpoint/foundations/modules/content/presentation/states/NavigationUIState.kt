package church.thegrowpoint.foundations.modules.content.presentation.states

import church.thegrowpoint.foundations.modules.content.Routes

/**
 * # NavigationUIState
 *
 * @property selectedSectionRoute The selected section route.
 */
data class NavigationUIState(
    val selectedSectionRoute: String = Routes.GETTING_STARTED.route
)
