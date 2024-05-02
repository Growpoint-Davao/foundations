package church.thegrowpoint.foundations.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import church.thegrowpoint.foundations.R

enum class DialogAction {
    CONFIRM, CANCEL
}

@Composable
fun ActionableDialog(
    dialogTitle: String? = null,
    dialogText: String? = null,
    confirmButtonText: String? = null,
    dismissButtonText: String? = null,
    icon: ImageVector? = null,
    onDismissRequest: (() -> Unit)? = null,
    onAction: (dialogAction: DialogAction) -> Unit
) {
    AlertDialog(
        icon = {
            if (icon != null) {
                Icon(icon, contentDescription = null)
            }
        },
        title = {
            if (dialogTitle != null) {
                Text(text = dialogTitle)
            } else {
                Text(text = stringResource(R.string.confirm_question))
            }
        },
        text = {
            if (dialogText != null) {
                Text(text = dialogText)
            } else {
                Text(text = stringResource(R.string.please_confirm))
            }
        },
        onDismissRequest = {
            if (onDismissRequest != null) {
                onDismissRequest()
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onAction(DialogAction.CONFIRM)
                }
            ) {
                if (confirmButtonText != null) {
                    Text(confirmButtonText)
                } else {
                    Text(stringResource(R.string.confirm))
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onAction(DialogAction.CANCEL)
                }
            ) {
                if (dismissButtonText != null) {
                    Text(dismissButtonText)
                } else {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    )
}
