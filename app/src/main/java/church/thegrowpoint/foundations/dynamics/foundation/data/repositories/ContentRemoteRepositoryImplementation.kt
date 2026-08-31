package church.thegrowpoint.foundations.dynamics.foundation.data.repositories

import church.thegrowpoint.foundations.dynamics.foundation.data.datasources.ContentRemoteDataSource
import church.thegrowpoint.foundations.dynamics.foundation.domain.repositories.ContentRemoteRepository

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
