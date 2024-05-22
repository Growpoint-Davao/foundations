package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.composables.RoundedTextInputField

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    value: String = "",
    imeAction: ImeAction = ImeAction.Next,
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
            imeAction = imeAction
        ),
        isError = isError,
        supportText = stringResource(R.string.please_provide_a_valid_email)
    )
}
