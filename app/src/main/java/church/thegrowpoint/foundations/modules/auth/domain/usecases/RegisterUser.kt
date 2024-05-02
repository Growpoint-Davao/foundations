package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * # RegisterUser
 *
 * Registers user using email and password.
 *
 * @property authRepository the auth repository instance.
 */
class RegisterUser @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * This function enables the parent class to be callable / invokable like function.
     *
     * This function needs the [email] and [password] of the user.
     *
     * @return it returns the user result during registration.
     */
    suspend operator fun invoke(email: String, password: String): AuthRepository.UserResult? {
        return authRepository.register(email = email, password = password)
    }
}
