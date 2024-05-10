package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Creates a page content composable.
 * This will hold the text contents and input fields for each field of the section page.
 *
 * TODO: We need to test this
 */
@Composable
fun PageContent(modifier: Modifier = Modifier, items: List<String>) {
    LazyColumn (modifier = modifier) {
        items(items) { item ->
            ContentMarkdown(markdown = item, modifier = modifier)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
