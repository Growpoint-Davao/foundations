package church.thegrowpoint.foundations.modules.content.presentation.pages.discipleship

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Discipleship(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: DiscipleshipViewModel = hiltViewModel()
) {
    // this is the first page so restore everything here
    val localPowerAnswers = viewModel.getDataStoreAnswersFlow().collectAsState(
        initial = HashMap()
    )
    LaunchedEffect(localPowerAnswers.value) {
        // restore answers
        viewModel.setAnswersState(localPowerAnswers.value)
    }

    val answers = viewModel.uiState.collectAsState().value.answers
    val howToGiveTestimony1 = answers["howToGiveTestimony1"] ?: ""
    val howToGiveTestimony2 = answers["howToGiveTestimony2"] ?: ""
    val howToGiveTestimony3 = answers["howToGiveTestimony3"] ?: ""
    val answer1 = answers["1"] ?: ""
    val answer2a = answers["2a"] ?: ""
    val answer2b = answers["2b"] ?: ""
    val answer3 = answers["3"] ?: ""

    LazyColumn(
        modifier = modifier.imePadding(),
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_1))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_2_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_3_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                maxLines = 5,
                value = howToGiveTestimony1
            ) {
                viewModel.updateAnswerState(key = "howToGiveTestimony1", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_3_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                maxLines = 5,
                value = howToGiveTestimony2
            ) {
                viewModel.updateAnswerState(key = "howToGiveTestimony2", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_3_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                maxLines = 5,
                value = howToGiveTestimony3,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "howToGiveTestimony3", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_3_part_4))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_4_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer1
            ) {
                viewModel.updateAnswerState(key = "1", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_4_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                maxLines = 5,
                value = answer2a,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "2a", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_4_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_5_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer2b
            ) {
                viewModel.updateAnswerState(key = "2b", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_5_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                maxLines = 5,
                value = answer3,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "3", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_5_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.discipleship_page_6))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
