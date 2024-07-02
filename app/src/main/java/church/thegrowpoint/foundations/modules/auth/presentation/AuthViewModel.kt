package church.thegrowpoint.foundations.modules.auth.presentation

import android.app.Activity
import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.SkipAuthCodes
import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetDataStoreSkipAuthFlow
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SendResetPasswordLink
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.UpdateDataStoreSkipAuthFlow
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
    private val getSkipAuthFlowUseCase: GetDataStoreSkipAuthFlow,
    private val updateSkipAuthFlowUseCase: UpdateDataStoreSkipAuthFlow,
    private val sendResetPasswordLinkUseCase: SendResetPasswordLink,
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
    fun skipAuthFlow(): Flow<Int?> {
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
        onSuccessMessage: String? = null,
        onRegistrationComplete: ((user: User?) -> Unit)? = null
    ) {
        viewModelScope.launch(dispatcher) {
            val result = registerUserUseCase(email = email, password = password)
            if (result != null) {
                result.user?.let {
                    if (onRegistrationComplete != null) {
                        onRegistrationComplete(it)
                    }

                    setCurrentUser(it)

                    if (onSuccessMessage != null) {
                        showToastMessage(onSuccessMessage)
                    }
                }
            }

            // TODO: send email verification and logout the user
        }
    }

    fun signIn(
        email: String,
        password: String,
        onSignIn: ((user: User?, exception: Exception?) -> Unit)? = null
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val signInResult = signInWithEmailAndPasswordUseCase(email = email, password = password)
                val user = signInResult?.user
                val exception = signInResult?.exception

                if (onSignIn != null) {
                    onSignIn(user, exception)
                }

                if (exception !=null) {
                    exception.message?.let { showToastMessage(it) }
                }

                setCurrentUser(user)
            } catch (e: Exception) {
                e.printStackTrace()

                e.message?.let { showToastMessage(it) }
            }
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

                    if (userResult.exception != null) {
                        (onErrorMessage ?: userResult.exception.message)?.let { showToastMessage(it) }
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

    /**
     * Sends a reset password link to the user.
     *
     * @param email The email of the user.
     * @param onSuccess A function to be called when the reset password link is sent.
     */
    fun sendResetPasswordLink(email: String, onSuccess: ((success: Boolean) -> Unit)) {
        viewModelScope.launch(dispatcher) {
            sendResetPasswordLinkUseCase(email) { success, exception ->
                onSuccess(success)
                exception?.message?.let { showToastMessage(it) }
            }
        }
    }
}
