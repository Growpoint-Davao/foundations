package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * # SendResetPasswordLink
 *
 * The use case for sending a reset password link to the user.
 *
 * @property authRepository the auth repository instance.
 */
class SendResetPasswordLink @Inject constructor(private val authRepository: AuthRepository) {
    /**
     * Sends a reset password link to the user.
     *
     * @param email The email address of the user.
     * @param onComplete The callback function to be invoked when the operation is complete.
     */
    suspend operator fun invoke(email: String, onComplete: (success: Boolean, exception: Exception?) -> Unit) {
        return authRepository.sendResetPasswordLink(email = email, onComplete = onComplete)
    }
}
