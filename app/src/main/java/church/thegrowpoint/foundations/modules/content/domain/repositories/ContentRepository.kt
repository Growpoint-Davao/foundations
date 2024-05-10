package church.thegrowpoint.foundations.modules.content.domain.repositories

/**
 * # ContentRepository
 *
 * The content repository interface for accessing app content.
 */
interface ContentRepository {
    /**
     * Retrieves the section content by the [name] of the section.
     *
     * @return an list of pages for a section with list of content.
     */
    fun section(name: String): List<List<String>>?
}
