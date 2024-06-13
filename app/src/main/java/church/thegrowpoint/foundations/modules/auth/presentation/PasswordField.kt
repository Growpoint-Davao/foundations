package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.ErrorLabel
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

@Composable
fun PasswordField(
    supportingText: String,
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = stringResource(R.string.password),
    isError: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    onValueChange: (String) -> Unit,
    onPasswordDone: (KeyboardActionScope.() -> Unit)? = null
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
        label = { Text(label) },
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
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onDone = onPasswordDone
        )
    )
}
