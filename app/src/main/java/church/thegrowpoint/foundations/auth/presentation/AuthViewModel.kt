package church.thegrowpoint.foundations.auth.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * *AuthViewModel*
 *
 * The auth view model class.
 */
@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    // auth state
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    /**
     * Sets the current authenticated [user].
     */
    fun setCurrentUser(user: FirebaseUser? = null) {
        // update the current user state
        _authState.update { currentState ->
            currentState.copy(
                currentUser = user
            )
        }
    }
}
