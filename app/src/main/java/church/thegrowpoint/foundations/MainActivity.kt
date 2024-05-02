package church.thegrowpoint.foundations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.modules.auth.presentation.AuthState
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.auth.presentation.LoginScreen
import church.thegrowpoint.foundations.modules.content.presentation.Introduction
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
                    AppContent(authViewModel = authViewModel)
                }
            }
        }
    }
}

/**
 * Resolves the start destination of the app. It needs the [authState] to determine the exact route.
 *
 * @return it returns the exact route.
 */
fun resolveStartDestination(authState: AuthState): String {
    if (authState.skipAuth || authState.currentUser != null) {
        // TODO: check what specific page to start
        return Routes.GETTING_STARTED.name
    }

    return Routes.LOGIN.name
}

@Composable
fun AppContent(
    authViewModel: AuthViewModel,
    navController: NavHostController = rememberNavController()
) {
    val authState by authViewModel.authState.collectAsState()

    // determine start destination
    val startDestination = resolveStartDestination(authState)

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routes.LOGIN.name) {
            LoginScreen(authViewModel = authViewModel)
        }

        composable(route = Routes.GETTING_STARTED.name) {
            Introduction()
        }
    }
}
