package church.thegrowpoint.foundations.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import church.thegrowpoint.foundations.ui.theme.dancingScriptFontFamily
import java.util.Locale

@Composable
fun GrowpointTitlePanel(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    logo: Painter? = null
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            Text(
                text = stringResource(R.string.growpoint).uppercase(Locale.ROOT),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )

            Text(
                text = title.uppercase(Locale.ROOT),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = dancingScriptFontFamily
            )
        }

        if (logo != null) {
            // TODO: Remove spacer once the original (dark logo) is square and edge to edge
            if (!isSystemInDarkTheme()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
            Image(painter = logo, contentDescription = null)
            // TODO: Remove spacer once the original (dark logo) is square and edge to edge
            if (!isSystemInDarkTheme()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GrowpointTitlePanelPreview() {
    FoundationsTheme {
        GrowpointTitlePanel(
            title = stringResource(R.string.foundations),
            subTitle = stringResource(R.string.established_for_growing),
            logo = painterResource(R.drawable.gp_login_logo)
        )
    }
}
