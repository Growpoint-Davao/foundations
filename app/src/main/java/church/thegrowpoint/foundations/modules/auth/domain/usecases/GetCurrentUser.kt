package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * # GetCurrentUser
 *
 * The use case for retrieving currently authenticated user if there's any.
 *
 * @property authRepository the auth repository instance.
 */
class GetCurrentUser @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * This function enables the parent class to be callable / invokable like function.
     *
     * @return it returns the currently authenticated user, if none then null.
     */
    operator fun invoke(): User? {
        return authRepository.currentUser
    }
}
