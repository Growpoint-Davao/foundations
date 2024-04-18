package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

@Composable
fun RoundedTextInputField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedShapes.large
    )
}
