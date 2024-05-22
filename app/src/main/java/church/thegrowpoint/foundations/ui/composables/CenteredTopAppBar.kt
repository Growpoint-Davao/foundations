package church.thegrowpoint.foundations.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

/**
 * Creates a centered [title] top app bar. Provide optional [scrollBehavior] if there's any and [onNavigationIconClick] callback.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navIcon: ImageVector = Icons.Filled.Menu,
    navIconContentDescription: String? = null,
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, overflow = TextOverflow.Ellipsis)
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = navIcon,
                    contentDescription = navIconContentDescription
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}
