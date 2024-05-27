package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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
    supportText: String,
    modifier: Modifier = Modifier,
    value: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    LabeledWithSupportTextOutlinedTextField(
        label = label,
        value = value,
        supportText = supportText,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedShapes.large,
        keyboardOptions = keyboardOptions,
        isError = isError
    )
}

@Composable
fun LabeledWithSupportTextOutlinedTextField(
    label: String,
    modifier: Modifier = Modifier,
    supportText: String = "",
    value: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    supportingText: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        keyboardOptions = keyboardOptions,
        isError = isError,
        singleLine = singleLine,
        maxLines = maxLines,
        supportingText = supportingText ?: {
            if (isError) {
                ErrorLabel(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportText
                )
            }
        }
    )
}

@Composable
fun MultilineLabeledWithSupportTextOutlinedTextField(
    label: String,
    modifier: Modifier = Modifier,
    supportText: String = "",
    value: String = "",
    maxLines: Int = 3,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
    isError: Boolean = false,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    supportingText: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    Column (modifier = modifier.padding(horizontal = 32.dp)) {
        LabeledWithSupportTextOutlinedTextField(
            label = label,
            modifier = Modifier.fillMaxWidth(),
            supportText = supportText,
            value = value,
            singleLine = false,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            isError = isError,
            shape = shape,
            supportingText = supportingText,
            onValueChange = onValueChange
        )
    }
}
