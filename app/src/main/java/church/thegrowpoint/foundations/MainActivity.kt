package church.thegrowpoint.foundations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.auth.presentation.LoginScreen
import church.thegrowpoint.foundations.modules.content.presentation.FoundationsContent
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoundationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // color = MaterialTheme.colorScheme.background
                ) {
                    val authState by authViewModel.authState.collectAsState()

                    if (authState.skipAuth || authState.currentUser != null) {
                        FoundationsContent(authViewModel = authViewModel)
                    } else {
                        LoginScreen(authViewModel = authViewModel)
                    }
                }
            }
        }
    }
}