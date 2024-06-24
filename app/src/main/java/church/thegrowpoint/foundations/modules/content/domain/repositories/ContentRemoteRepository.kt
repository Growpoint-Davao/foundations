package church.thegrowpoint.foundations.modules.content.domain.repositories

/**
 * # ContentRemoteRepository
 *
 * The interface for the remote repository.
 */
interface ContentRemoteRepository {
    /**
     * Write the data to the remote repository.
     *
     * @param data The data to write.
     * @return The data that was written.
     */
    suspend fun write(data: Map<String, Any?>): Any?

    /**
     * Read the data from the remote repository.
     *
     * @return The data that was read.
     */
    suspend fun read(): Map<String, Any?>?
}
