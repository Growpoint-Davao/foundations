package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.ContentRemoteDataSource
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRemoteRepository

/**
 * # ContentRemoteRepositoryImplementation
 *
 * @property contentRemoteDataSource The content remote data source instance.
 */
class ContentRemoteRepositoryImplementation(
    private val contentRemoteDataSource: ContentRemoteDataSource
): ContentRemoteRepository {
    /**
     * Writes data to the content remote data source.
     *
     * @param data The data to write.
     * @return The written data.
     */
    override suspend fun write(data: Map<String, Any?>): Any? {
        return contentRemoteDataSource.write(data)
    }

    /**
     * Reads data from the content remote data source.
     *
     * @return The data that was read.
     */
    override suspend fun read(): Map<String, Any?>? {
        return contentRemoteDataSource.read()
    }
}
