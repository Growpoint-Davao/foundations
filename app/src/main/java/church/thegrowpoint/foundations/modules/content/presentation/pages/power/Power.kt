package church.thegrowpoint.foundations.modules.content.presentation.pages.power

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Power(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: PowerViewModel = hiltViewModel(),
    header: @Composable (() -> Unit)? = null
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
    val answer1 = answers["1"] ?: ""
    val answer2 = answers["2"] ?: ""
    val answer3 = answers["3"] ?: ""
    val answer4 = answers["4"] ?: ""
    val answer5 = answers["5"] ?: ""
    val answer6 = answers["6"] ?: ""
    val answer7 = answers["7"] ?: ""
    val answer8 = answers["8"] ?: ""
    val answer9a = answers["9a"] ?: ""
    val answer9b = answers["9b"] ?: ""

    LazyColumn(
        modifier = modifier.imePadding(),
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(24.dp))
            }
            ContentMarkdown(markdown = stringResource(R.string.power_page_1_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_2_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer1
            ) {
                viewModel.updateAnswerState(key = "1", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_2_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer2
            ) {
                viewModel.updateAnswerState(key = "2", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_2_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_3_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer3,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "3", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_3_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer4
            ) {
                viewModel.updateAnswerState(key = "4", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_3_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer5
            ) {
                viewModel.updateAnswerState(key = "5", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_3_part_4))
            Spacer(modifier = Modifier.height(8.dp))

            ContentMarkdown(markdown = stringResource(R.string.power_page_4_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer6
            ) {
                viewModel.updateAnswerState(key = "6", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_4_part_2))
            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_4_part_3))
            Spacer(modifier = Modifier.height(8.dp))

            ContentMarkdown(markdown = stringResource(R.string.power_page_5_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer8,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "8", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_5_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_6_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer9a
            ) {
                viewModel.updateAnswerState(key = "9a", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_6_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer9b,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "9b", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.power_page_6_part_3))
            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}
