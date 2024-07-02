package church.thegrowpoint.foundations.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import church.thegrowpoint.foundations.R

@Composable
fun SimpleAlertDialog(
    dialogTitle: String,
    dialogText: String,
    buttonText: String? = null,
    onDismissRequest: (() -> Unit)? = null,
    onAction: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            if (onDismissRequest != null) {
                onDismissRequest()
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onAction()
                }
            ) {
                if (buttonText != null) {
                    Text(buttonText)
                } else {
                    Text(stringResource(R.string.ok))
                }
            }
        },
        dismissButton = {}
    )
}
