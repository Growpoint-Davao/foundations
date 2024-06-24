package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.modules.content.presentation.states.NavigationDrawerItemsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * # ContentViewModel
 *
 * The view model for app content.
 *
 * @property appContext The application context.
 */
@HiltViewModel
class ContentViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel(context) {
    // auth state
    private val _navigationDrawerItemsUIState = MutableStateFlow(NavigationDrawerItemsUIState())
    val navigationDrawerItemsUIState: StateFlow<NavigationDrawerItemsUIState> =
        _navigationDrawerItemsUIState.asStateFlow()

    /**
     * The section pages configuration.
     */
    private val sectionPagesConfig = mapOf(
        Routes.GETTING_STARTED.route to mapOf(
            "titleResID" to R.string.getting_started,
            "pages" to 3,
            "next" to Routes.SALVATION.route
        ),
        Routes.SALVATION.route to mapOf(
            "titleResID" to R.string.salvation,
            "previous" to Routes.GETTING_STARTED.route,
            "pages" to 10,
            "next" to Routes.LORDSHIP.route
        ),
        Routes.LORDSHIP.route to mapOf(
            "titleResID" to R.string.lordship,
            "previous" to Routes.SALVATION.route,
            "pages" to 7,
            "next" to Routes.IDENTITY.route
        ),
        Routes.IDENTITY.route to mapOf(
            "titleResID" to R.string.identity,
            "previous" to Routes.LORDSHIP.route,
            "pages" to 7,
            "next" to Routes.POWER.route
        ),
        Routes.POWER.route to mapOf(
            "titleResID" to R.string.power,
            "previous" to Routes.IDENTITY.route,
            "pages" to 7,
            "next" to Routes.DEVOTION.route
        ),
        Routes.DEVOTION.route to mapOf(
            "titleResID" to R.string.devotion,
            "previous" to Routes.POWER.route,
            "pages" to 5,
            "next" to Routes.CHURCH.route
        ),
        Routes.CHURCH.route to mapOf(
            "titleResID" to R.string.church,
            "previous" to Routes.DEVOTION.route,
            "pages" to 3,
            "next" to Routes.DISCIPLESHIP.route
        ),
        Routes.DISCIPLESHIP.route to mapOf(
            "titleResID" to R.string.discipleship,
            "previous" to Routes.CHURCH.route,
            "pages" to 5
        )
    )

    /**
     * Retrieves the number of pages in a section.
     *
     * @param name The section name.
     */
    fun getSectionPageCount(name: String): Int {
        val pages = sectionPagesConfig[name]?.get("pages")
        if (pages is Int) {
            return pages
        }

        return 0
    }

    /**
     * Retrieves the previous section name.
     *
     * @param name The current section name.
     */
    fun getNextSection(name: String): String? {
        val next = sectionPagesConfig[name]?.get("next")
        if (next is String) {
            return next
        }

        return null
    }

    /**
     * Retrieves the title resource ID for a section.
     *
     * @param name The section name.
     */
    fun getTitleResource(name: String): String? {
        val id = sectionPagesConfig[name]?.get("titleResID")
        if (id is Int) {
            return appContext.getString(id)
        }

        return null
    }

    /**
     * Sets the currently selected navigation drawer item.
     *
     * @param gettingStartedSelected If true, it will highlight the getting started drawer item as selected.
     * @param salvationSelected If true, it will highlight the salvation drawer item as selected.
     * @param lordshipSelected If true, it will highlight the deity drawer item as selected.
     * @param identitySelected If true, it will highlight the identity drawer item as selected.
     * @param powerSelected If true, it will highlight the power drawer item as selected.
     * @param devotionSelected If true, it will highlight the devotion drawer item as selected.
     * @param churchSelected If true, it will highlight the church drawer item as selected.
     * @param discipleshipSelected If true, it will highlight the discipleship drawer item as selected.
     */
    fun setNavigationDrawerItemSelected(
        gettingStartedSelected: Boolean = false,
        salvationSelected: Boolean = false,
        lordshipSelected: Boolean = false,
        identitySelected: Boolean = false,
        powerSelected: Boolean = false,
        devotionSelected: Boolean = false,
        churchSelected: Boolean = false,
        discipleshipSelected: Boolean = false
    ) {
        _navigationDrawerItemsUIState.update { currentState ->
            currentState.copy(
                gettingStartedSelected = gettingStartedSelected,
                salvationSelected = salvationSelected,
                lordshipSelected = lordshipSelected,
                identitySelected = identitySelected,
                powerSelected = powerSelected,
                devotionSelected = devotionSelected,
                churchSelected = churchSelected,
                discipleshipSelected = discipleshipSelected
            )
        }
    }

    /**
     * Sets the currently selected navigation drawer item.
     *
     * @param section The section name.
     */
    fun setNavigationDrawerItemSelected(section: String) {
        when (section) {
            Routes.GETTING_STARTED.route -> setNavigationDrawerItemSelected(gettingStartedSelected = true)
            Routes.SALVATION.route -> setNavigationDrawerItemSelected(salvationSelected = true)
            Routes.LORDSHIP.route -> setNavigationDrawerItemSelected(lordshipSelected = true)
            Routes.IDENTITY.route -> setNavigationDrawerItemSelected(identitySelected = true)
            Routes.POWER.route -> setNavigationDrawerItemSelected(powerSelected = true)
            Routes.DEVOTION.route -> setNavigationDrawerItemSelected(devotionSelected = true)
            Routes.CHURCH.route -> setNavigationDrawerItemSelected(churchSelected = true)
            else -> setNavigationDrawerItemSelected(discipleshipSelected = true)
        }
    }
}
