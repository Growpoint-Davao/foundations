package church.thegrowpoint.foundations.modules.content.presentation.pages.devotion

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Devotion3(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: DevotionViewModel = hiltViewModel()
) {
    val answers = viewModel.uiState.collectAsState().value.answers
    val answer4 = answers["4"] ?: ""
    val answer5 = answers["5"] ?: ""
    val answer6 = answers["6"] ?: ""
    val answer7 = answers["7"] ?: ""

    LazyColumn(
        modifier = Modifier.imePadding(),
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_3_part_1),
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
                value = answer4
            ) {
                viewModel.updateAnswerState(key = "4", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_3_part_2),
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
                value = answer5
            ) {
                viewModel.updateAnswerState(key = "5", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_3_part_3),
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
                value = answer6
            ) {
                viewModel.updateAnswerState(key = "6", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_3_part_4),
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
                value = answer7,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "7", answer = it)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
