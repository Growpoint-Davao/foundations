package church.thegrowpoint.foundations.modules.auth.domain.usecases

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject

/**
 * # SignInWithGoogle
 *
 * The use case for singing user to the app using their Google account.
 *
 * @property authRepository the auth repository instance.
 * @property appContext the instance of application context for creating auth credential and getting resources.
 * @constructor creates an instance of the use case but usually done via dependency injection through hilt.
 */
class SignInWithGoogle @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext context: Context
) {
    /**
     * Application context instance.
     */
    private var appContext: Context = context

    /**
     * Executes signing of user using their Google account.
     *
     * The [attempt] is the number attempt, because this function is called recursively in case of failures.
     */
    private suspend fun googleSignIn(attempt: Int = 1): GetCredentialResponse? {
        if (attempt > 3) {
            // TODO: This means that there is no google account signed in the device, so ask user to log a google account
            throw Exception("Reached maximum retry attempts")
        }

        try {
            val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(attempt == 1)
                    .setServerClientId(appContext.getString(R.string.default_web_client_id))
                    .setAutoSelectEnabled(true)
                    .setNonce(UUID.randomUUID().toString())
                    .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(appContext)

            return credentialManager.getCredential(
                request = request,
                context = appContext,
            )
        } catch (e: GetCredentialException) {
            e.printStackTrace()

            if (e is NoCredentialException) {
                // run again to force the un-authorize flag to false
                return googleSignIn(attempt + 1)
            } else if (e !is GetCredentialCancellationException) {
                throw e
            }
        }

        return null
    }

    /**
     * It handles google sign-in result and then will sign-in the user to firebase.
     *
     * The [result] argument is the credential response from Google sign-in.
     */
    private suspend fun handleGoogleSignIn(result: GetCredentialResponse): AuthRepository.UserResult? {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate to firebase.
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)

                        val firebaseCredential = GoogleAuthProvider.getCredential(
                            googleIdTokenCredential.idToken, null
                        )

                        return authRepository.signInWithCredential(firebaseCredential)
                    } catch (e: GoogleIdTokenParsingException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.e("GOOGLE_CREDENTIAL", "Unexpected type of credential")
                }
            }
            else -> {
                // Catch any unrecognized credential type here.
                Log.e("GOOGLE_CREDENTIAL", "Unexpected type of credential")
            }
        }

        return null
    }

    /**
     * This function enables the parent class to be callable / invokable like function.
     */
    suspend operator fun invoke(): AuthRepository.UserResult? {
        val result = googleSignIn()
        return result?.let { handleGoogleSignIn(it) }
    }
}
