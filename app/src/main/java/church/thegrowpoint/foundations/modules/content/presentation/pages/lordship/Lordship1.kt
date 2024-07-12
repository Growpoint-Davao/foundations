package church.thegrowpoint.foundations.modules.content.presentation.pages.lordship

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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel

@Composable
fun Lordship1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: LordshipViewModel = hiltViewModel()
) {
    // this is the first page so restore everything here
    val localLordShipAnswers = viewModel.getDataStoreAnswersFlow().collectAsState(
        initial = HashMap()
    )
    LaunchedEffect(localLordShipAnswers.value) {
        // restore answers
        viewModel.setAnswersState(localLordShipAnswers.value)
    }

    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.lordship_page_1),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}