package church.thegrowpoint.foundations.auth.domain.usecases

import android.content.Context
import church.thegrowpoint.foundations.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetGoogleSignInClient @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }
}
