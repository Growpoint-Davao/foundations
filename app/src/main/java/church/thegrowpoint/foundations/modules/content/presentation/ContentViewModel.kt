package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import church.thegrowpoint.foundations.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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

    fun getSectionPageCount(name: String): Int {
        val pages = sectionPagesConfig[name]?.get("pages")
        if (pages is Int) {
            return pages
        }

        return 0
    }

    fun getNextSection(name: String): String? {
        val next = sectionPagesConfig[name]?.get("next")
        if (next is String) {
            return next
        }

        return null
    }

    fun getTitleResource(name: String): String? {
        val id = sectionPagesConfig[name]?.get("titleResID")
        if (id is Int) {
            return appContext.getString(id)
        }

        return null
    }

    // TODO: create more methods for other content
}
