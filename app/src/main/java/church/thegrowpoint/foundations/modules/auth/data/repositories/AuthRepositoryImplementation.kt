package church.thegrowpoint.foundations.modules.auth.data.repositories

import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSource
import church.thegrowpoint.foundations.modules.auth.data.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository.UserResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
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
    private val authLocalDataSource: AuthLocalDataSource
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
    override fun getSkipAuthFlow(): Flow<Int> {
        return authLocalDataSource.getSkipAuthFlow()
    }

    /**
     * Updates skip auth flow for skip auth state.
     */
    override suspend fun updateSkipAuthFlow(value: Int) {
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
                return UserResult(user = user)
            }
        } catch (e: Exception) {
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
                return UserResult(user = user)
            }
        } catch (e: Exception) {
            return UserResult(exception = e)
        }

        return null
    }
}
