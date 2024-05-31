package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # GetDataStoreSkipAuthFlow
 *
 * The use case for retrieving the skip auth flow from the AuthRepository.
 *
 * @property authRepository The AuthRepository instance.
 */
class GetDataStoreSkipAuthFlow @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * Retrieves the skip auth flow from the AuthRepository.
     *
     * @return the flow state for skip auth.
     */
    operator fun invoke(): Flow<Int?> {
        return authRepository.getDataStoreSkipAuthFlow()
    }
}
