package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUser @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): AuthRepository.UserResult? {
        return authRepository.register(email = email, password = password)
    }
}
