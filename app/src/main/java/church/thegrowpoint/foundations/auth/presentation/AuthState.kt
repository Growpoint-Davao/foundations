package church.thegrowpoint.foundations.auth.presentation

import church.thegrowpoint.foundations.auth.domain.models.User

data class AuthState (
    val currentUser: User? = null
)
