package church.thegrowpoint.foundations.modules.auth.presentation

import church.thegrowpoint.foundations.modules.auth.domain.models.User

/**
 * # AuthState
 *
 * The auth state data class.
 *
 * @property currentUser the currently authenticated user.
 * @property skipAuth If true user opted authentication and all data will not be save to the cloud.
 */
data class AuthState (
    /**
     * The currently authenticated user.
     */
    val currentUser: User? = null,

    /**
     * If this flag is true, user opted authentication
     */
    val skipAuth: Boolean = false
)
