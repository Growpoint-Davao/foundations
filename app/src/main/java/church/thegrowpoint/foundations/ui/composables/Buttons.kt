package church.thegrowpoint.foundations.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.NavigateBefore
import androidx.compose.material.icons.automirrored.twotone.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.theme.RoundedShapes

/**
 * Creates and icon button that complements the color of the surface.
 *
 * I needs a [icon], [text], and [contentDescription].
 * It has an optional [modifier] parameter.
 */
@Composable
fun SurfaceThemedIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            // .background(Color.White, shape = RoundedShapes.large)
            .background(MaterialTheme.colorScheme.inverseSurface, shape = RoundedShapes.large)
            .fillMaxWidth()
            .padding(all = 8.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}

/**
 * Creates a large button.
 *
 * It needs a [text] as the text for this button.
 * It has an optional [modifier] parameter.
 */
@Composable
fun LargeButton(
    modifier: Modifier = Modifier,
    text: String,
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

/**
 * Creates an animated two floating action buttons for navigation.
 * The [onPreviousClick] callback will be called when you click previous button, and
 * the [onNextClick] will be called when you click next button.
 * To animate the auto hiding, you can set [isVisible] to false.
 */
@Composable
fun AnimatedNavigationFloatingActionButtons(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    isVisible: Boolean = true
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = slideOutVertically(
            animationSpec = keyframes {
                this.durationMillis = 150
            }
        ) + fadeOut()
    ) {
        Row {
            FloatingActionButton(shape = RoundedShapes.large, onClick = onPreviousClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.TwoTone.NavigateBefore,
                    contentDescription = stringResource(R.string.previous)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            FloatingActionButton(shape = RoundedShapes.large, onClick = onNextClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.TwoTone.NavigateNext,
                    contentDescription = stringResource(R.string.next)
                )
            }
        }
    }
}
