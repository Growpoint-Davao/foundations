package church.thegrowpoint.foundations.modules.content.presentation.states

/**
 * # NavigationDrawerItemsUIState
 *
 * @property gettingStartedSelected If true, it will highlight the getting started drawer item as selected.
 * @property salvationSelected If true, it will highlight the salvation drawer item as selected.
 * @property lordshipSelected If true, it will highlight the deity drawer item as selected.
 * @property identitySelected If true, it will highlight the identity drawer item as selected.
 * @property powerSelected If true, it will highlight the power drawer item as selected.
 * @property devotionSelected If true, it will highlight the devotion drawer item as selected.
 * @property churchSelected If true, it will highlight the church drawer item as selected.
 * @property discipleshipSelected If true, it will highlight the discipleship drawer item as selected.
 */
data class NavigationDrawerItemsUIState(
    val gettingStartedSelected: Boolean = false,
    val salvationSelected: Boolean = false,
    val lordshipSelected: Boolean = false,
    val identitySelected: Boolean = false,
    val powerSelected: Boolean = false,
    val devotionSelected: Boolean = false,
    val churchSelected: Boolean = false,
    val discipleshipSelected: Boolean = false
)
