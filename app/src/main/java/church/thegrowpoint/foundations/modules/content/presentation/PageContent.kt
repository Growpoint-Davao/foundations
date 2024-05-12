package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.ui.composables.RoundedTextInputField

/**
 * Creates a page content composable.
 * This will hold the text contents and input fields for each field of the section page.
 *
 * TODO: We need to test this
 */
@Composable
fun PageContent(
    modifier: Modifier = Modifier,
    items: List<String>,
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = state) {
        items(items) { item ->
            Spacer(modifier = Modifier.height(16.dp))
            if (item == "INPUT_FIELD") {
                RoundedTextInputField(supportingText = "", label = "") {

                }
            }
            ContentMarkdown(markdown = item, modifier = modifier)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
