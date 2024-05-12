package church.thegrowpoint.foundations.modules.content.presentation

import androidx.lifecycle.ViewModel
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * # ContentViewModel
 *
 * The view model for app content.
 *
 * @property getContent The use case for getting content section.
 */
@HiltViewModel
class ContentViewModel @Inject constructor(private val getContent: GetContent) : ViewModel() {
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
