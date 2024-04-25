package church.thegrowpoint.foundations.auth.domain.repositories

import church.thegrowpoint.foundations.auth.domain.models.User
import com.google.firebase.auth.AuthCredential

/**
 * *AuthRepository*
 *
 * The repository interface for the authentication related processes.
 *
 * @property currentUser the current authenticated user.
 */
interface AuthRepository {
    val currentUser: User?

    /**
     * Signs out the current user.
     */
    fun signOut()

    /**
     * Registers the user using his / her own [email] and [password].
     * It has callback function for getting the newly registered user and exception error if there's any.
     */
    fun register(
        email: String,
        password: String,
        onResult: (user: User?, error: Exception?) -> Unit
    )

    /**
     * Signs in user using his / her [email] and [password].
     * It has a callback function for getting the signed user object and exception if something went wrong.
     */
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onResult: (user: User?, error: Exception?) -> Unit
    )

    /**
     * Signs using firebase [AuthCredential].
     * It has a callback function for getting the signed user object and exception if something went wrong.
     */
    fun firebaseSignIn(
        credential: AuthCredential,
        onResult: (user: User?, error: Exception?) -> Unit
    )
}
