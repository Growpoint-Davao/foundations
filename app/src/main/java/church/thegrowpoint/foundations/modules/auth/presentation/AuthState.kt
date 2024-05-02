package church.thegrowpoint.foundations.modules.auth.presentation

import church.thegrowpoint.foundations.modules.auth.domain.models.User

/**
 * # AuthState
 *
 * The auth state data class.
 *
 * @property currentUser the currently authenticated user.
 */
data class AuthState (
    /**
     * The currently authenticated user.
     */
    val currentUser: User? = null
)
