package church.thegrowpoint.foundations.modules.auth.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.ui.composables.ActionableDialog
import church.thegrowpoint.foundations.ui.composables.ClickableLabel
import church.thegrowpoint.foundations.ui.composables.DialogAction
import church.thegrowpoint.foundations.ui.composables.LargeButton
import church.thegrowpoint.foundations.ui.composables.SurfaceThemedIconButton
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import church.thegrowpoint.foundations.utils.extensions.getActivity
import church.thegrowpoint.foundations.utils.extensions.validEmail
import church.thegrowpoint.foundations.utils.extensions.validPasswordLength
import java.util.Locale

/**
 * Creates a login screen for the app.
 *
 * This function requires the [authViewModel] and optional [modifier]
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    appNavController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    var pwLengthValid by rememberSaveable { mutableStateOf(true) }
    var enableSignInButton by rememberSaveable {
        mutableStateOf((email.isNotEmpty() && password.isNotEmpty()))
    }

    enableSignInButton =
        pwLengthValid && isValidEmail && (email.isNotEmpty() && password.isNotEmpty())

    val openSkipSignInDialog = rememberSaveable { mutableStateOf(false) }

    if (openSkipSignInDialog.value) {
        ActionableDialog(
            dialogTitle = stringResource(R.string.skip_authentication_question),
            dialogText = stringResource(R.string.skip_authentication_text),
            confirmButtonText = stringResource(R.string.yes),
            dismissButtonText = stringResource(R.string.no),
            onDismissRequest = { openSkipSignInDialog.value = false }
        ) { dialogAction ->

            if (dialogAction == DialogAction.CONFIRM) {
                // change the auth state to skipped
                authViewModel.skipAuthentication()
            }

            // close the dialog
            openSkipSignInDialog.value = false
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = 32.dp)
            .safeDrawingPadding()
    ) {
        Text(
            text = stringResource(R.string.growpoint).uppercase(Locale.ROOT),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = stringResource(R.string.foundations).uppercase(Locale.ROOT),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Image(painter = painterResource(R.drawable.gp_login_logo), contentDescription = null)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.sign_in_to_your_account),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
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
            isError = !pwLengthValid,
            supportingText = stringResource(R.string.password_is_too_short)
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_in),
            enabled = enableSignInButton
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        ClickableLabel(
            text = stringResource(R.string.forgot_the_password),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.or_continue_with),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(16.dp))
            SurfaceThemedIconButton(
                modifier = Modifier.weight(1f),
                icon = painterResource(R.drawable.google_logo),
                text = stringResource(R.string.google)
            ) {
                authViewModel.signInWithGoogle(
                    activity = context.getActivity(),
                    onErrorMessage = context.getString(R.string.unable_to_sign_in_with_google_the_device_probable_not_supported)
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.do_not_have_an_account),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            ClickableLabel(
                text = stringResource(R.string.skip_sign_in),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ) {
                // change the open dialog state
                openSkipSignInDialog.value = true

            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.or),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            ClickableLabel(
                text = stringResource(R.string.sign_up),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ) {
                appNavController.navigate(Routes.REGISTER.route)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun LoginPreview() {
    FoundationsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(authViewModel = hiltViewModel())
        }
    }
}
