package church.thegrowpoint.foundations.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R

@Composable
fun NavigationDrawerItemWithProgress(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    icon: Painter? = null,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        modifier = modifier.padding(vertical = 8.dp),
        icon = {
            if (icon != null) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = icon,
                    contentDescription = null
                )
            }
        },
        label = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                if (subTitle != null) {
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        },
        selected = selected,
        onClick = onClick
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NavigationDrawerItemWithProgressPreview() {
    NavigationDrawerItemWithProgress(
        title = stringResource(R.string.getting_started),
        subTitle = stringResource(R.string.introduction),
        icon = painterResource(R.drawable.getting_started)
    ) {

    }
}
