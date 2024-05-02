package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
 *
 * @property signOutUser the use case for signing out user.
 * @property registerUser the use case for registering the user.
 * @property signInWithEmailAndPassword the use case for signing user with email and password.
 * @property signInWithGoogle the use case for signing user with Google account.
 * @property dispatcher an instance of coroutine dispatcher.
 * @constructor this is usually being created via hilt.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    getCurrentUser: GetCurrentUser,
    private val signOutUser: SignOutUser,
    private val registerUser: RegisterUser,
    private val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    private val signInWithGoogle: SignInWithGoogle,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    // auth state
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        // sets the current user
        setCurrentUser(getCurrentUser())

        // TODO: read local storage to check if user did opted out authentication
    }

    /**
     * Sets the current authenticated [user].
     */
    private fun setCurrentUser(user: User?) {
        // update the current user state
        _authState.update { currentState ->
            currentState.copy(currentUser = user)
        }
    }

    /**
     * Logs out the user.
     */
    fun logout() {
        signOutUser()
        setCurrentUser(null)
    }

    fun register(
        email: String,
        password: String,
        onRegistrationComplete: (user: User?) -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
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
        viewModelScope.launch(dispatcher) {
            val signInResult = signInWithEmailAndPassword(email = email, password = password)
            val user = signInResult?.user
            val exception = signInResult?.exception
            onSignIn(user, exception)
            setCurrentUser(user)
        }
    }

    /**
     * Signs user using their Google account.
     *
     * It accepts a call back function [onGoogleSignIn] which accepts user instance and exception.
     * If the user is not null then the user has successfully signed-in otherwise exception will not be null.
     */
    fun signInWithGoogle(onGoogleSignIn: (user: User?, exception: Exception?) -> Unit) {
        viewModelScope.launch(dispatcher) {
            val userResult = signInWithGoogle()
            if (userResult != null) {
                val user = userResult.user
                setCurrentUser(user)
                onGoogleSignIn(userResult.user, userResult.exception)
            }
        }
    }
}
