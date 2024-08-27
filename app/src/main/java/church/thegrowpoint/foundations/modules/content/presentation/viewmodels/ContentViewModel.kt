package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.content.Routes
import church.thegrowpoint.foundations.modules.content.presentation.states.NavigationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

enum class Sections(
    @StringRes val title: Int,
    @StringRes val subTitle: Int,
    @DrawableRes val icon: Int,
    val baseRoute: String
) {
    GETTING_STARTED(
        title = R.string.getting_started,
        subTitle = R.string.introduction,
        icon = R.drawable.getting_started,
        baseRoute = Routes.GETTING_STARTED.route
    ),
    SALVATION(
        title = R.string.salvation,
        subTitle = R.string.lesson_1,
        icon = R.drawable.salvation,
        baseRoute = Routes.SALVATION.route
    ),
    LORDSHIP(
        title = R.string.lordship,
        subTitle = R.string.lesson_2,
        icon = R.drawable.lordship,
        baseRoute = Routes.LORDSHIP.route
    ),
    IDENTITY(
        title = R.string.identity,
        subTitle = R.string.lesson_3,
        icon = R.drawable.identity,
        baseRoute = Routes.IDENTITY.route
    ),
    POWER(
        title = R.string.power,
        subTitle = R.string.lesson_4,
        icon = R.drawable.power,
        baseRoute = Routes.POWER.route
    ),
    DEVOTION(
        title = R.string.devotion,
        subTitle = R.string.lesson_5,
        icon = R.drawable.devotion,
        baseRoute = Routes.DEVOTION.route
    ),
    CHURCH(
        title = R.string.church,
        subTitle = R.string.lesson_6,
        icon = R.drawable.church,
        baseRoute = Routes.CHURCH.route
    ),
    DISCIPLESHIP(
        title = R.string.discipleship,
        subTitle = R.string.lesson_7,
        icon = R.drawable.discipleship,
        baseRoute = Routes.DISCIPLESHIP.route
    )
}

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
    private val _navigationUIState = MutableStateFlow(NavigationUIState())
    val navigationUIState: StateFlow<NavigationUIState> = _navigationUIState.asStateFlow()

    fun setSelectedSectionRoute(section: Routes) {
        setSelectedSectionRoute(section.route)
    }

    fun setSelectedSectionRoute(section: String) {
        _navigationUIState.update { currentState ->
            currentState.copy(
                selectedSectionRoute = section
            )
        }
    }

    /**
     * Retrieves section title resource from sections enum.
     *
     * @param sectionRoute The section route.
     * @return The section title resource id.
     */
    fun getSectionTitleResource(sectionRoute: String): Int {
        val selectedRoute = Sections.entries.first {
            it.baseRoute == sectionRoute
        }

        return selectedRoute.title
    }
}
