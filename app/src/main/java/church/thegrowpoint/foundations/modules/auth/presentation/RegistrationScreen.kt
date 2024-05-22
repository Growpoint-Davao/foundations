package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    appNavController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenteredTopAppBar(
                title = stringResource(R.string.register),
                navIconContentDescription = stringResource(R.string.register),
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationIconClick = {
                    appNavController.popBackStack()
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {

            }
        }
    )
}
