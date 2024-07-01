package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import church.thegrowpoint.foundations.ui.theme.RoundedShapes
import church.thegrowpoint.foundations.utils.extensions.validEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    appNavController: NavHostController = rememberNavController(),
) {
    var email by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenteredTopAppBar(
                title = stringResource(R.string.forgot_the_password),
                navIconContentDescription = stringResource(R.string.forgot_the_password),
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationIconClick = {
                    appNavController.popBackStack()
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .imePadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Column(modifier = modifier.padding(horizontal = 32.dp)) {
                    EmailField(
                        value = email,
                        onValueChange = {
                            email = it.trim()
                            isValidEmail = email.validEmail()
                        },
                        imeAction = ImeAction.Done,
                        isError = !isValidEmail
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedShapes.large,
                onClick = {
                    if (!isValidEmail || email.isBlank()) {
                        authViewModel.showToastMessage(context.getString(R.string.please_provide_a_valid_email))
                        return@FloatingActionButton
                    }

                    // send reset password link via email

                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Send,
                    contentDescription = stringResource(R.string.send_reset_password_link)
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordPreview() {
    FoundationsTheme {
        ForgotPasswordScreen()
    }
}
