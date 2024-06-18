package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Save
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.theme.RoundedShapes
import church.thegrowpoint.foundations.utils.extensions.validEmail
import church.thegrowpoint.foundations.utils.extensions.validPasswordLength

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    appNavController: NavHostController = rememberNavController(),
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    var pwLengthValid by rememberSaveable { mutableStateOf(true) }
    var confirmPwLengthValid by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current

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
                modifier = modifier
                    .padding(innerPadding)
                    .imePadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Column(modifier = modifier.padding(horizontal = 32.dp)) {
                    Spacer(modifier = Modifier.height(32.dp))
                    EmailField(
                        value = email,
                        onValueChange = {
                            email = it.trim()
                            isValidEmail = email.validEmail()
                        },
                        isError = !isValidEmail
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordField(
                        value = password,
                        onValueChange = {
                            password = it
                            pwLengthValid = password.validPasswordLength()
                        },
                        imeAction = ImeAction.Next,
                        isError = !pwLengthValid || (password != confirmPassword),
                        supportingText = if (!pwLengthValid) stringResource(R.string.password_is_too_short) else stringResource(
                            R.string.passwords_do_not_match
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PasswordField(
                        value = confirmPassword,
                        label = stringResource(R.string.confirm_password),
                        onValueChange = {
                            confirmPassword = it
                            confirmPwLengthValid = confirmPassword.validPasswordLength()
                        },
                        isError = !confirmPwLengthValid,
                        supportingText = stringResource(R.string.password_is_too_short)
                    ) {
                        authViewModel.register(
                            email = email,
                            password = password,
                            onSuccessMessage = context.getString(R.string.registration_is_successful)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(shape = RoundedShapes.large, onClick = {
                authViewModel.register(
                    email = email,
                    password = password,
                    onSuccessMessage = context.getString(R.string.registration_is_successful)
                )
            }) {
                Icon(
                    imageVector = Icons.Rounded.Save,
                    contentDescription = stringResource(R.string.save)
                )
            }
        }
    )
}
