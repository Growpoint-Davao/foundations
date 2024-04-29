package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class SignOutUser @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() {
        return authRepository.signOut()
    }
}
