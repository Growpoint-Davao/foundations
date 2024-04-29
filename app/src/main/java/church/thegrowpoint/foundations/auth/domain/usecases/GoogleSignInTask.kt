package church.thegrowpoint.foundations.auth.domain.usecases

import android.content.Intent
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleSignInTask @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(intent: Intent): AuthRepository.UserResult? {
        val account = GoogleSignIn.getSignedInAccountFromIntent(intent).await()
        val idToken = account.idToken!!
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return authRepository.signInWithCredential(credential)
    }
}
