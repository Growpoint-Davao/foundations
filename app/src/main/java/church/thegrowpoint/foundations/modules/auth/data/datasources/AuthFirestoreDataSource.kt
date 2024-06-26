package church.thegrowpoint.foundations.modules.auth.data.datasources

import church.thegrowpoint.foundations.modules.FirestoreDataSource
import church.thegrowpoint.foundations.modules.auth.data.models.User
import church.thegrowpoint.foundations.modules.auth.data.models.toAnyMap
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * # AuthFirestoreDataSource
 *
 * Interface for user related operations to firebase data source.
 */
interface AuthFirestoreDataSource {
    /**
     * Store user in firebase.
     *
     * @param user the user model instance.
     */
    suspend fun storeUser(user: User): Any?
}

/**
 * # AuthFirestoreDataSourceImplementation
 *
 * The implementation of [AuthFirestoreDataSource] which extends [FirestoreDataSource].
 * This is the repository class for user related operations in the context of Firestore.
 *
 * @property db the instance of [FirebaseFirestore]
 */
class AuthFirestoreDataSourceImplementation @Inject constructor(db: FirebaseFirestore) :
    FirestoreDataSource(db), AuthFirestoreDataSource
{
    /**
     * Store user in firebase.
     *
     * @param user the user model instance.
     */
    override suspend fun storeUser(user: User): Any? {
        if (user.id.isBlank()) {
            return write(collection = "users", data = user.toAnyMap())
        }

        return write(collection = "users", data = user.toAnyMap(), docId = user.id)
    }
}
