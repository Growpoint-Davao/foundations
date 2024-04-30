package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * # SignOutUser
 *
 * The use case for signing out the user from the app.
 *
 * @property authRepository the auth repository instance.
 * @constructor creates an instance of the use case but usually done via dependency injection through hilt.
 */
class SignOutUser @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * This function enables the parent class to be callable / invokable like function.
     */
    operator fun invoke() {
        return authRepository.signOut()
    }
}
