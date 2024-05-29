package church.thegrowpoint.foundations.modules.auth.presentation

import android.app.Activity
import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.SkipAuthCodes
import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetSkipAuthFlow
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.UpdateSkipAuthFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
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
 * @property signOutUserUseCase the use case for signing out user.
 * @property registerUserUseCase the use case for registering the user.
 * @property signInWithEmailAndPasswordUseCase the use case for signing user with email and password.
 * @property signInWithGoogleUseCase the use case for signing user with Google account.
 * @property dispatcher an instance of coroutine dispatcher.
 * @constructor this is usually being created via hilt.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext context: Context,
    getCurrentUser: GetCurrentUser,
    private val signOutUserUseCase: SignOutUser,
    private val registerUserUseCase: RegisterUser,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPassword,
    private val signInWithGoogleUseCase: SignInWithGoogle,
    private val getSkipAuthFlowUseCase: GetSkipAuthFlow,
    private val updateSkipAuthFlowUseCase: UpdateSkipAuthFlow,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel(context) {
    // auth state
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        // sets the current user
        setCurrentUser(getCurrentUser())

        // TODO: read local storage to check if user did opted out authentication
    }

    /**
     * Retrieves the skip auth flow.
     *
     * @return the skip auth flow.
     */
    fun skipAuthFlow(): Flow<Int> {
        return getSkipAuthFlowUseCase()
    }

    /**
     * Skips the authentication.
     */
    fun skipAuthentication() {
        _authState.update { currentState ->
            currentState.copy(skipAuth = true)
        }

        viewModelScope.launch(dispatcher) {
            updateSkipAuthFlowUseCase(1)
        }
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
        signOutUserUseCase()
        setCurrentUser(null)

        // make sure skip auth is false
        _authState.update { currentState ->
            currentState.copy(skipAuth = false)
        }

        viewModelScope.launch(dispatcher) {
            updateSkipAuthFlowUseCase(SkipAuthCodes.NOT_SKIPPED.code)
        }
    }

    fun register(
        email: String,
        password: String,
        onRegistrationComplete: (user: User?) -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            val result = registerUserUseCase(email = email, password = password)
            if (result != null) {
                result.user?.let {
                    onRegistrationComplete(it)
                    setCurrentUser(it)
                }
            }

            // TODO: send email verification and logout the user
        }
    }

    fun signIn(
        email: String,
        password: String,
        onSignIn: (user: User?, exception: Exception?) -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            val signInResult = signInWithEmailAndPasswordUseCase(email = email, password = password)
            val user = signInResult?.user
            val exception = signInResult?.exception
            onSignIn(user, exception)
            setCurrentUser(user)
        }
    }

    /**
     * Signs user using their Google account.
     *
     * @param activity the optional activity instance being used in case it is needed when signing with Google.
     * @param onErrorMessage the optional notification error message to show.
     * @param onGoogleSignIn the optional function to be called when the user is signed-in.
     */
    fun signInWithGoogle(
        activity: Activity? = null,
        onErrorMessage: String? = null,
        onGoogleSignIn: ((user: User?, exception: Exception?) -> Unit)? = null
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val userResult = signInWithGoogleUseCase(activity)
                if (userResult != null) {
                    val user = userResult.user
                    setCurrentUser(user)

                    if (onGoogleSignIn != null) {
                        onGoogleSignIn(userResult.user, userResult.exception)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

                (onErrorMessage ?: e.message)?.let { showToastMessage(it) }

                if (onGoogleSignIn != null) {
                    onGoogleSignIn(null, e)
                }
            }
        }
    }
}
