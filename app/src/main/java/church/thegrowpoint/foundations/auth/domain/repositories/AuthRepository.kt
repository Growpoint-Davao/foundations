package church.thegrowpoint.foundations.auth.domain.repositories

import church.thegrowpoint.foundations.auth.domain.models.User
import com.google.firebase.auth.AuthCredential

/**
 * # AuthRepository
 *
 * The repository interface for the authentication related processes.
 *
 * @property currentUser the current authenticated user.
 */
interface AuthRepository {
    /**
     * *UserResult*
     *
     * A simple data class wrapper for a user and possible exception during authentication.
     *
     * @property user an instance of a user.
     * @property exception a possible error exception.
     */
    data class UserResult (
        val user: User? = null,
        val exception: java.lang.Exception? = null
    )

    val currentUser: User?

    /**
     * Signs out the current user.
     */
    fun signOut()

    /**
     * Registers the user using his / her own [email] and [password].
     * It has callback function for getting the newly registered user and exception error if there's any.
     *
     * @return returns [UserResult], a wrapper data class that contains the authenticated user and possible exception.
     */
    suspend fun register(email: String, password: String): UserResult?

    /**
     * Signs in user using his / her [email] and [password].
     * It has a callback function for getting the signed user object and exception if something went wrong.
     *
     * @return returns [UserResult], a wrapper data class that contains the authenticated user and possible exception.
     */
    suspend fun signInWithEmailAndPassword(email: String, password: String): UserResult?

    /**
     * Signs using firebase [AuthCredential].
     * It has a callback function for getting the signed user object and exception if something went wrong.
     * This can be used when supporting Facebook, Google, and other social medial sign-in methods.
     *
     * @return returns [UserResult], a wrapper data class that contains the authenticated user and possible exception.
     */
    suspend fun signInWithCredential(credential: AuthCredential): UserResult?
}
