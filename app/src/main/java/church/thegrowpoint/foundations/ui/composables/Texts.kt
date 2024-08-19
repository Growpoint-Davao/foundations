package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
 * 
 * @param modifier [Modifier] to alter this composable.
 * @param text The text to show.
 * @param fontWeight The weight of the text.
 * @param color The color of the text.
 * @param onClick The callback to be invoked when this label is clicked.
 */
@Composable
fun ClickableLabel(
    modifier: Modifier = Modifier,
    text: String,
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
 * @param modifier [Modifier] to alter this composable.
 * @param text The error text to show.
 */
@Composable
fun ErrorLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.error
    )
}

/**
 * Creates a title label that has subtitle.
 *
 * @param modifier [Modifier] to alter this composable.
 * @param title The title to show.
 * @param subTitle The subtitle to show.
 */
@Composable
fun TitleLabel(modifier: Modifier = Modifier, title: String, subTitle: String? = null) {
    Column (modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        if (!subTitle.isNullOrBlank()) {
            Text(
                text = subTitle,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
