package church.thegrowpoint.foundations.auth.presentation

import com.google.firebase.auth.FirebaseUser

data class AuthState (
    val currentUser: FirebaseUser? = null
)

