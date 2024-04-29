package church.thegrowpoint.foundations.auth.presentation

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.ClickableLabel
import church.thegrowpoint.foundations.ui.composables.ErrorLabel
import church.thegrowpoint.foundations.ui.composables.LargeButton
import church.thegrowpoint.foundations.ui.composables.RoundedTextInputField
import church.thegrowpoint.foundations.ui.composables.WhiteIconButton
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import church.thegrowpoint.foundations.ui.theme.RoundedShapes
import church.thegrowpoint.foundations.utils.extensions.validEmail
import church.thegrowpoint.foundations.utils.extensions.validPasswordLength
import java.util.Locale

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    var pwLengthValid by rememberSaveable { mutableStateOf(true) }
    var enableSignInButton by rememberSaveable {
        mutableStateOf((email.isNotEmpty() && password.isNotEmpty()))
    }

    enableSignInButton = pwLengthValid && isValidEmail && (email.isNotEmpty() && password.isNotEmpty())

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            authViewModel.googleSign(result) {
                    user, exception ->

                if (user != null) {
                    Log.d("AUTH", user.email)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 32.dp)
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
        )
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
            WhiteIconButton(
                modifier = Modifier.weight(1f),
                imageRes = R.drawable.facebook_logo,
                labelRes = R.string.facebook
            ) {

            }
            Spacer(modifier = Modifier.width(16.dp))
            WhiteIconButton(
                modifier = Modifier.weight(1f),
                imageRes = R.drawable.google_logo,
                labelRes = R.string.google
            ) {
                launcher.launch(authViewModel.createGoogleSignInClientIntent())
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
                text = stringResource(R.string.sign_up),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ) {

            }
        }
    }
}

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    RoundedTextInputField(
        modifier = modifier,
        label = stringResource(R.string.email),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        isError = isError,
        supportingText = stringResource(R.string.please_provide_a_valid_email)
    )
}

@Composable
fun PasswordField(
    supportingText: String,
    modifier: Modifier = Modifier,
    value: String = "",
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedShapes.large,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorLabel(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText
                )
            }
        },
        label = { Text(stringResource(R.string.password)) },
        placeholder = { Text(text = stringResource(R.string.enter_password)) },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    Icons.Filled.Visibility,
                    contentDescription = stringResource(R.string.show_hide_password)
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
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
            LoginScreen(
                authViewModel = hiltViewModel()
            )
        }
    }
}
