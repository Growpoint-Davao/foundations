package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * # UpdateDataStoreSkipAuthFlow
 *
 * The use case for updating the skip auth
 *
 * @property authRepository The AuthRepository instance.
 */
class UpdateDataStoreSkipAuthFlow @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * Updates the skip auth state.
     *
     * @param value The value to update the skip auth to.
     */
    suspend operator fun invoke(value: Int) {
        return authRepository.updateDataStoreSkipAuth(value)
    }
}
