package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

/**
 * Creates a rounded and outlined text input field.
 *
 * It requires a [label], [supportingText] to be used when there is an error, initial [value], and trailing lambda [onValueChange] as a callback.
 * It has optional arguments as well, [modifier] to modify the style if the input field, [keyboardOptions], and [isError].
 */
@Composable
fun RoundedTextInputField(
    label: String,
    supportingText: String,
    modifier: Modifier = Modifier,
    value: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedShapes.large,
        keyboardOptions = keyboardOptions,
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorLabel(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText
                )
            }
        }
    )
}
