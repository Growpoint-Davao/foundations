package church.thegrowpoint.foundations.modules.content.presentation.pages.power

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown

@Composable
fun Power1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(state = state) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.power_page_1),
                modifier = modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
