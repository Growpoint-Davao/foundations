package church.thegrowpoint.foundations.modules

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

/**
 * # FirebaseDataSource
 *
 * The base class for all Firebase data sources.
 *
 * @property db the firestore database instance.
 */
abstract class FirestoreDataSource(private val db: FirebaseFirestore) {
    /**
     * Writes a new data to a certain collection.
     *
     * @param collection the collection name where to write the data.
     * @param data the data to write.
     * @return returns the firestore document reference.
     */
    protected suspend fun write(collection: String, data: HashMap<String, Any>): DocumentReference? {
        return db.collection(collection).add(data).await()
    }

    /**
     * Writes a new data or update existing data to a certain collection.
     *
     * @param collection the collection name where to write the data.
     * @param docId the document id of the data to be merge or update.
     * @param data the data to write.
     */
    protected suspend fun write(
        collection: String,
        docId: String,
        data: HashMap<String, Any>
    ): Any? {
        val ref = db.collection(collection).document(docId)
        if (docId.isNotEmpty()) {
            return ref.set(data, SetOptions.merge()).await()
        }

        return ref.set(data).await()
    }

    // TODO: More functions here
}
