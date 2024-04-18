package church.thegrowpoint.foundations.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ClickableLabel(
    modifier: Modifier = Modifier,
    @StringRes
    textRes: Int,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickable(onClick = onClick),
        text = stringResource(textRes),
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight ?: FontWeight.Bold,
        color = color
    )
}
