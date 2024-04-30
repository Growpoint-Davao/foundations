package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

/**
 * Creates a clickable label.
 *
 * It needs the [text] to show.
 * It has optional arguments such as [modifier] to alter this composable, [fontWeight],
 * [color], and trailing lambda [onClick] callback.
 */
@Composable
fun ClickableLabel(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickable(onClick = onClick),
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight ?: FontWeight.Bold,
        color = color
    )
}

/**
 * Creates error label.
 *
 * It needs the [text] to show and optional [modifier].
 */
@Composable
fun ErrorLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.error
    )
}
