package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

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
