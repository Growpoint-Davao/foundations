package church.thegrowpoint.foundations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.auth.presentation.LoginScreen
import church.thegrowpoint.foundations.modules.content.presentation.FoundationsContent
import church.thegrowpoint.foundations.modules.content.presentation.Routes
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
                    val navController = rememberNavController()

                    var startDestination = Routes.AUTH.name
                    if (authState.skipAuth || authState.currentUser != null) {
                        startDestination = Routes.CONTENT.name
                    }

                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        navigation(
                            startDestination = Routes.LOGIN.name,
                            route = Routes.AUTH.name
                        ) {
                            composable(route = Routes.LOGIN.name) {
                                LoginScreen(authViewModel = authViewModel)
                            }

                            composable(route = Routes.REGISTER.name) {
                                // TODO: register
                                Text(text = "Register")
                            }

                            composable(route = Routes.FORGOT_PASSWORD.name) {
                                // TODO: register
                                Text(text = "Forgout Password")
                            }
                        }

                        composable(route = Routes.CONTENT.name) {
                            FoundationsContent(authViewModel = authViewModel)
                        }
                    }
                }
            }
        }
    }
}
