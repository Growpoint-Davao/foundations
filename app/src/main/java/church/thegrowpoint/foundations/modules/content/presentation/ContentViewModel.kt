package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * # ContentViewModel
 *
 * The view model for app content.
 *
 * @property getContent The use case for getting content section.
 */
@HiltViewModel
class ContentViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getContent: GetContent
) : ViewModel() {
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

    private var appContext: Context = context

    /**
     * Retrieves the getting started section.
     *
     * @return The getting started section.
     */
    fun getGettingStarted() = getContent("gettingStarted")

    /**
     * Retrieves the salvation section.
     *
     * @return The salvation section.
     */
    fun getSalvation() = getContent("salvation")

    fun getSectionPages(name: String): Int {
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

    /**
     * The extension class that retrieves a list of strings from a list of lists using an [index].
     *
     * @return The list of strings. If the index is out of bounds, an empty list is returned.
     */
    private fun List<List<String>>.retrieve(index: Int): List<String> {
        return try {
            this[index]
        } catch (e: ArrayIndexOutOfBoundsException) {
            listOf()
        } catch (e: IndexOutOfBoundsException) {
            listOf()
        }
    }

    /**
     * Retrieves the contents of the getting started section.
     * Provide the index / page of the section you wish to retrieve.
     *
     * @return The page contents of getting started section.
     */
    fun getGettingStartedPageContents(index: Int): List<String> {
        val section = getGettingStarted() ?: return listOf()

        return section.retrieve(index)
    }

    /**
     * Retrieves the contents of the salvation section.
     * Provide the index / page of the section you wish to retrieve.
     *
     * @return The page contents of salvation section.
     */
    fun getSalvationPageContents(index: Int): List<String> {
        val section = getSalvation() ?: return listOf()

        return section.retrieve(index)
    }

    // TODO: create more methods for other content
}
