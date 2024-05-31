package church.thegrowpoint.foundations.modules.content.presentation.pages.salvation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Salvation6(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: SalvationViewModel = hiltViewModel()
) {
    val answers = viewModel.salvationAnswersUIState.collectAsState().value.answers
    val answer9 = answers["9"] ?: ""
    val answer10 = answers["10"] ?: ""

    LazyColumn(
        modifier = Modifier.imePadding(),
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.salvation_page_6_part_1),
                modifier = modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer9
            ) {
                viewModel.setSalvationAnswer(key = "9", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.salvation_page_6_part_2),
                modifier = modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer10
            ) {
                viewModel.setSalvationAnswer(key = "10", answer = it)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
