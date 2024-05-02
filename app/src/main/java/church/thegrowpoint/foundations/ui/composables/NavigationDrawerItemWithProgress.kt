package church.thegrowpoint.foundations.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NavigationDrawerItemWithProgress(
    title: String,
    subTitle: String? = null,
    icon: ImageVector? = null,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        icon = {
            if (icon != null) {
                Icon(icon, contentDescription = null)
            }
        },
        label = {
            Column {
                Text(text = title)

                if (subTitle != null) {
                    Text(text = subTitle)
                }
            }
        },
        selected = selected,
        onClick = onClick
    )
}
