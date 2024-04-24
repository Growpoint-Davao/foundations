package church.thegrowpoint.foundations.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

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
