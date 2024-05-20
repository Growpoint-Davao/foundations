package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import church.thegrowpoint.foundations.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
class ContentViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    // auth state
    private val _navigationDrawerItemsUIState = MutableStateFlow(NavigationDrawerItemsUIState())
    val navigationDrawerItemsUIState: StateFlow<NavigationDrawerItemsUIState> =
        _navigationDrawerItemsUIState.asStateFlow()

    /**
     * The section pages configuration.
     */
    private val sectionPagesConfig = mapOf(
        "gettingStarted" to mapOf(
            "titleResID" to R.string.getting_started,
            "pages" to 3,
            "next" to "salvation"
        ),
        "salvation" to mapOf(
            "titleResID" to R.string.salvation,
            "previous" to "gettingStarted",
            "pages" to 10,
            "next" to "lordship"
        )
    )

    /**
     * The application context.
     */
    private var appContext: Context = context

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

    // TODO: create more methods for other content
}
