package church.thegrowpoint.foundations.modules.content.presentation.pages.identity

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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Identity5(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: IdentityViewModel = hiltViewModel()
) {
    val answers = viewModel.uiState.collectAsState().value.answers
    val answer5 = answers["5"] ?: ""
    val answer6 = answers["6"] ?: ""

    LazyColumn(
        modifier = modifier.imePadding(),
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.identity_page_5_part_1),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
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
                markdown = stringResource(R.string.identity_page_5_part_2),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
                )
            )
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer6,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "6", answer = it)
            }
            ContentMarkdown(
                markdown = stringResource(R.string.identity_page_5_part_3),
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
