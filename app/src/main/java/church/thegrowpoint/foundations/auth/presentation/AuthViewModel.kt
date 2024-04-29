package church.thegrowpoint.foundations.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.auth.domain.models.User
import church.thegrowpoint.foundations.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.auth.domain.usecases.SignOutUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * # AuthViewModel
 *
 * The auth view model class.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    getCurrentUser: GetCurrentUser,
    private val signOutUser: SignOutUser,
    private val registerUser: RegisterUser,
    private val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    private val signInWithGoogle: SignInWithGoogle
) : ViewModel() {
    // auth state
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        setCurrentUser(getCurrentUser())
    }

    /**
     * Sets the current authenticated [user].
     */
    fun setCurrentUser(user: User?) {
        // update the current user state
        _authState.update { currentState ->
            currentState.copy(
                currentUser = user
            )
        }
    }

    fun logout() {
        signOutUser()
        setCurrentUser(null)
    }

    fun register(
        email: String,
        password: String,
        onRegistrationComplete: (user: User?) -> Unit
    ) {
        viewModelScope.launch {
            val result = registerUser(email = email, password = password)
            if (result != null) {
                result.user?.let { onRegistrationComplete(it) }
            }

            // logout after registration because firebase logs the user after registration
            logout()
        }
    }

    fun signIn(
        email: String,
        password: String,
        onSignIn: (user: User?, exception: Exception?) -> Unit
    ) {
        viewModelScope.launch {
            val signInResult = signInWithEmailAndPassword(email = email, password = password)
            val user = signInResult?.user
            val exception = signInResult?.exception
            onSignIn(user, exception)
            setCurrentUser(user)
        }
    }

    fun signInWithGoogle(onGoogleSignIn: (user: User?, exception: Exception?) -> Unit) {
        viewModelScope.launch {
            val userResult = signInWithGoogle()
            if (userResult != null) {
                val user = userResult.user
                setCurrentUser(user)
                onGoogleSignIn(userResult.user, userResult.exception)
            }
        }
    }
}
