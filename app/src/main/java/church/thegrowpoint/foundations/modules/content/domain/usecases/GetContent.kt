package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRepository
import javax.inject.Inject

/**
 * # GetContent
 *
 * The use case for retrieving section content from the repository.
 *
 * @property contentRepository The repository to retrieve the content from.
 */
class GetContent @Inject constructor(private val contentRepository: ContentRepository) {
    /**
     * Retrieves the content for the given section.
     * This makes the instance of this class to become invokable.
     *
     * @return an list of pages for a section with list of content.
     */
    operator fun invoke(name: String): List<List<String>>? {
        return contentRepository.section(name)
    }
}
