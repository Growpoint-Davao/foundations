package church.thegrowpoint.foundations.modules.content.presentation.pages.lordship

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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Lordship2(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: LordshipViewModel = hiltViewModel()
) {
    val answers = viewModel.uiState.collectAsState().value.answers
    val answer1 = answers["1"] ?: ""

    LazyColumn(
        modifier = modifier.imePadding(),
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.lordship_page_2_part_1),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
                )
            )
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "1", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.lordship_page_2_part_2),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
