package church.thegrowpoint.foundations.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

@Composable
fun WhiteIconButton(
    @DrawableRes
    imageRes: Int,
    @StringRes
    labelRes: Int,
    modifier: Modifier = Modifier,
    @StringRes
    contentDescriptionRes: Int? = null,
    onClick: () -> Unit
) {
    val contentDescription =
        if (contentDescriptionRes != null) stringResource(contentDescriptionRes) else null

    IconButton(
        modifier = modifier
            .background(Color.White, shape = RoundedShapes.large)
            .fillMaxWidth()
            .padding(all = 8.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(imageRes),
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = labelRes), fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun LargeButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(modifier = modifier, onClick = onClick, enabled = enabled) {
        Text(
            modifier = Modifier.padding(all = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}