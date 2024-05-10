package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

/**
 * Creates a markdown composable element.
 *
 * TODO: We need to test this.
 */
@Composable
fun ContentMarkdown(modifier: Modifier = Modifier, markdown: String) {
    MarkdownText(
        modifier = modifier.padding(all = 8.dp),
        markdown = markdown,
        style = MaterialTheme.typography.bodyLarge
    )
}
