package church.thegrowpoint.foundations.modules.content.presentation.pages.salvation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.ContentViewModel

@Composable
fun Salvation1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentViewModel: ContentViewModel = hiltViewModel()
) {
    // this is the first page so restore everything here
    val localSalvationAnswers = contentViewModel.getDataStoreSalvationAnswersFlow().collectAsState(
        initial = HashMap()
    )
    LaunchedEffect(localSalvationAnswers.value) {
        if (localSalvationAnswers.value.size > 0) {
            // restore salvation answers
            contentViewModel.setSalvationAnswers(localSalvationAnswers.value)
        }
    }

    LazyColumn(state = state) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.salvation_page_1),
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