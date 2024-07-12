package church.thegrowpoint.foundations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.modules.SkipAuthCodes
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.auth.presentation.ForgotPasswordScreen
import church.thegrowpoint.foundations.modules.auth.presentation.NoRegistrationLoginScreen
import church.thegrowpoint.foundations.modules.auth.presentation.RegistrationScreen
import church.thegrowpoint.foundations.modules.content.presentation.FoundationsContent
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ChurchViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val salvationViewModel: SalvationViewModel by viewModels()
    private val lordShipViewModel: LordshipViewModel by viewModels()
    private val identityViewModel: IdentityViewModel by viewModels()
    private val powerViewModel: PowerViewModel by viewModels()
    private val devotionViewModel: DevotionViewModel by viewModels()
    private val churchViewModel: ChurchViewModel by viewModels()
    private val discipleshipViewModel: DiscipleshipViewModel by viewModels()
    private val contentViewModel: ContentViewModel by viewModels()

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

                        if (authState.currentUser != null) {
                            salvationViewModel.restoreAnswersFromRemoteResource()
                            lordShipViewModel.restoreAnswersFromRemoteResource()
                            identityViewModel.restoreAnswersFromRemoteResource()
                            powerViewModel.restoreAnswersFromRemoteResource()
                            devotionViewModel.restoreAnswersFromRemoteResource()
                            churchViewModel.restoreAnswersFromRemoteResource()
                            discipleshipViewModel.restoreAnswersFromRemoteResource()
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
                                    NoRegistrationLoginScreen(
                                        authViewModel = authViewModel
                                    )
                                }

                                composable(route = Routes.REGISTER.route) {
                                    RegistrationScreen(
                                        authViewModel = authViewModel,
                                        appNavController = navController
                                    )
                                }

                                composable(route = Routes.FORGOT_PASSWORD.route) {
                                    ForgotPasswordScreen(
                                        // authViewModel = authViewModel,
                                        appNavController = navController
                                    )
                                }
                            }

                            composable(route = Routes.CONTENT.route) {
                                // set the initial selected item
                                contentViewModel.setNavigationDrawerItemSelected(
                                    gettingStartedSelected = true
                                )

                                FoundationsContent(
                                    authViewModel = authViewModel,
                                    salvationViewModel = salvationViewModel,
                                    lordShipViewModel = lordShipViewModel,
                                    identityViewModel = identityViewModel,
                                    powerViewModel = powerViewModel,
                                    devotionViewModel = devotionViewModel,
                                    churchViewModel = churchViewModel,
                                    discipleshipViewModel = discipleshipViewModel,
                                    contentViewModel = contentViewModel
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

    override fun onStop() {
        super.onStop()

        // save answers to remote source
        salvationViewModel.saveAnswersToRemoteSource()
        lordShipViewModel.saveAnswersToRemoteSource()
        identityViewModel.saveAnswersToRemoteSource()
        powerViewModel.saveAnswersToRemoteSource()
        devotionViewModel.saveAnswersToRemoteSource()
        churchViewModel.saveAnswersToRemoteSource()
        discipleshipViewModel.saveAnswersToRemoteSource()
    }
}
