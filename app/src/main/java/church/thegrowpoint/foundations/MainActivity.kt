package church.thegrowpoint.foundations

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.modules.SkipAuthCodes
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.auth.presentation.LoginScreen
import church.thegrowpoint.foundations.modules.auth.presentation.RegistrationScreen
import church.thegrowpoint.foundations.modules.content.presentation.FoundationsContent
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            FoundationsTheme {
                val skipAuth = authViewModel.skipAuthFlow().collectAsState(
                    initial = SkipAuthCodes.INITIAL.code
                )

                if (skipAuth.value != SkipAuthCodes.INITIAL.code) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        // color = MaterialTheme.colorScheme.background
                    ) {
                        val authState by authViewModel.authState.collectAsState()
                        val navController = rememberNavController()

                        var startDestination = Routes.AUTH.route
                        if (skipAuth.value == SkipAuthCodes.SKIPPED.code || authState.currentUser != null) {
                            startDestination = Routes.CONTENT.route
                        }

                        NavHost(
                            navController = navController,
                            startDestination = startDestination
                        ) {
                            navigation(
                                startDestination = Routes.LOGIN.route,
                                route = Routes.AUTH.route
                            ) {
                                composable(route = Routes.LOGIN.route) {
                                    LoginScreen(
                                        authViewModel = authViewModel,
                                        appNavController = navController
                                    )
                                }

                                composable(route = Routes.REGISTER.route) {
                                    RegistrationScreen(
                                        authViewModel = authViewModel,
                                        appNavController = navController
                                    )
                                }

                                composable(route = Routes.FORGOT_PASSWORD.route) {
                                    // TODO: register
                                    Text(text = "Forgout Password")
                                }
                            }

                            composable(route = Routes.CONTENT.route) {
                                FoundationsContent(
                                    authViewModel = authViewModel,
                                    contentViewModel = hiltViewModel()
                                )
                            }
                        }
                    }
                } else {
                    // skip auth is at initial state so display a blank surface for now
                    Surface(modifier = Modifier.fillMaxSize()) {}
                }
            }
        }
    }
}
