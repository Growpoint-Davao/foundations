package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.ContentService
import church.thegrowpoint.foundations.modules.content.data.models.Content
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRepository
import javax.inject.Inject

/**
 * # ContentRepositoryImplementation
 *
 * The implementation class of the [ContentRepository] interface.
 *
 * @property contentService The [ContentService] instance.
 */
class ContentRepositoryImplementation @Inject constructor(
    private val contentService: ContentService
): ContentRepository {
    /**
     * Retrieves the section content by the [name] of the section.
     *
     * @return an list of pages for a section with list of content.
     */
    override fun section(name: String): List<List<String>>? {
        if (contentService.content == null) {
            return null
        }

        try {
            // use reflection to get the field by name
            val field = Content::class.java.getDeclaredField(name)
            field.isAccessible = true

            // invoke the field's getter method to get the value
            val value = field.get(contentService.content) ?: return null
            return value as List<List<String>>?
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return null
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
