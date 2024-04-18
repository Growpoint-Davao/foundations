package church.thegrowpoint.foundations.auth.presentation

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.ClickableLabel
import church.thegrowpoint.foundations.ui.composables.LargeButton
import church.thegrowpoint.foundations.ui.composables.RoundedTextInputField
import church.thegrowpoint.foundations.ui.composables.WhiteIconButton
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

@Composable
fun Login(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 32.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.gp_login_text_logo), contentDescription = null
        )
        Image(
            painter = painterResource(R.drawable.gp_login_logo), contentDescription = null
        )
        Text(
            text = stringResource(R.string.sign_in_to_your_account),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        RoundedTextInputField(
            label = stringResource(R.string.email),
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordField(
            value = password,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_in),
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.forgot_the_password),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.or_continue_with),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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

            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.do_not_have_an_account),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            ClickableLabel(
                textRes = R.string.sign_up,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ) {

            }
        }
    }
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedShapes.large,
        value = value,
        onValueChange = onValueChange,
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
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
    FoundationsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Login()
        }
    }
}
