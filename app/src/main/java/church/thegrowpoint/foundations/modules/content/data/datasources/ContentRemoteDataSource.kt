package church.thegrowpoint.foundations.modules.content.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * # ContentRemoteDataSource
 *
 * The interface for the remote content data source.
 */
interface ContentRemoteDataSource {
    /**
     * The user ID of the current user.
     */
    val userID: String

    /**
     * Write the given data to the remote data source.
     *
     * @param data The data to write.
     * @return The data written.
     */
    suspend fun write(data: Map<String, Any?>): Any?

    /**
     * Read the data from the remote data source.
     *
     * @return The read data.
     */
    suspend fun read(): Map<String, Any?>?
}

/**
 * # FirestoreRemoteDataSource
 *
 * The firestore data source implementation for this application.
 *
 * @property collection The collection name of this data source.
 * @property userID The user ID of the current user.
 * @property db The Firebase Firestore instance.
 */
class FirestoreRemoteDataSource(
    private val collection: String,
    override val userID: String,
    private val db: FirebaseFirestore
): ContentRemoteDataSource {
    /**
     * Write the given data to the remote data source.
     *
     * @param data The data to write.
     * @return The data written.
     */
    override suspend fun write(data: Map<String, Any?>): Any? {
        return db.collection(collection).document(userID).set(data).await()
    }

    /**
     * Read the data from the remote data source.
     *
     * @return The data that was read.
     */
    override suspend fun read(): Map<String, Any?>? {
        val doc = db.collection(collection).document(userID).get().await()
        return doc.data
    }
}
