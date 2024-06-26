package church.thegrowpoint.foundations.modules.auth.data.repositories

import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthFirestoreDataSource
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSource
import church.thegrowpoint.foundations.modules.auth.data.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository.UserResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import church.thegrowpoint.foundations.modules.auth.domain.models.User as DomainUser

/**
 * # AuthRepositoryImplementation
 *
 * Auth repository implementation.
 *
 * @constructor Creates an instance of the repository
 * @property firebaseAuth the instance of firebase auth.
 */
class AuthRepositoryImplementation @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authLocalDataSource: AuthLocalDataSource,
    private val authFirestoreDataSource: AuthFirestoreDataSource
) : AuthRepository {
    /**
     * Retrieves the current authenticated user
     */
    override val currentUser: DomainUser?
        get() {
            if (firebaseAuth.currentUser == null) {
                return null
            }

            return User(firebaseAuth.currentUser!!)
        }

    /**
     * Retrieves skip auth flow for skip auth state.
     *
     * @return returns a flow of int which represents the skip auth state.
     */
    override fun getDataStoreSkipAuthFlow(): Flow<Int?> {
        return authLocalDataSource.getSkipAuthFlow()
    }

    /**
     * Updates skip auth flow for skip auth state.
     */
    override suspend fun updateDataStoreSkipAuth(value: Int) {
        return authLocalDataSource.updateSkipAuth(value)
    }

    /**
     * Executes firebase sign-out.
     */
    override fun signOut() {
        return firebaseAuth.signOut()
    }

    /**
     * Registers user using [email] and [password].
     *
     * @return returns an instance of [UserResult].
     */
    override suspend fun register(email: String, password: String): UserResult? {
        try {
            val task = firebaseAuth.createUserWithEmailAndPassword(email, password)
            val result = task.await()
            if (result.user != null) {
                val user = User(result.user!!)

                // after successful registration we need to store the user in firestore
                storeUser(user)

                return UserResult(user = user)
            }
        } catch (e: Exception) {
            return UserResult(exception = e)
        }

        return null
    }

    /**
     * Signs user using his / her [email] and [password].
     *
     * @return returns an instance of [UserResult].
     */
    override suspend fun signInWithEmailAndPassword(email: String, password: String): UserResult? {
        try {
            val task = firebaseAuth.signInWithEmailAndPassword(email, password)
            val result = task.await()
            if (result.user != null) {
                val user = User(result.user!!)

                // after registration we need to store the user in firestore
                storeUser(user)

                return UserResult(user = user)
            }
        } catch (e: Exception) {
            return UserResult(exception = e)
        } catch (e: FirebaseFirestoreException) {
            // can't write the user to database so make sure that we logout the user first
            signOut()
            return UserResult(exception = e)
        }

        return null
    }

    /**
     * Signs user using firebase [credential].
     *
     * @return returns an instance of [UserResult].
     */
    override suspend fun signInWithCredential(credential: AuthCredential): UserResult? {
        try {
            val task = firebaseAuth.signInWithCredential(credential)
            val result = task.await()
            if (result.user != null) {
                val user = User(result.user!!)

                // after credential sign-in we need to store the user in firestore
                storeUser(user)

                return UserResult(user = user)
            }
        } catch (e: Exception) {
            return UserResult(exception = e)
        } catch (e: FirebaseFirestoreException) {
            // can't write the user to database so make sure that we logout the user first
            signOut()
            return UserResult(exception = e)
        }

        return null
    }

    /**
     * Stores user in firestore.
     *
     * @param user the user to be stored.
     */
    override suspend fun storeUser(user: DomainUser): Any? {
        return authFirestoreDataSource.storeUser(user as User)
    }

    /**
     * Sends reset password link to [email].
     *
     * @param email The email to send the link to.
     * @param onComplete The callback to be called when the operation is completed.
     */
    override suspend fun sendResetPasswordLink(
        email: String,
        onComplete: (success: Boolean, exception: Exception?) -> Unit
    ) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            onComplete(true, null)
        } catch (e: Exception) {
            onComplete(false, e)
        }
    }
}
