package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.models.User
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): User? {
        return authRepository.currentUser
    }
}
