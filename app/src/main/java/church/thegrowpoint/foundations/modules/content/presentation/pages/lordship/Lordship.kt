package church.thegrowpoint.foundations.modules.content.presentation.pages.lordship

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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Lordship(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: LordshipViewModel = hiltViewModel(),
    header: @Composable (() -> Unit)? = null
) {
    // this is the first page so restore everything here
    val localLordShipAnswers = viewModel.getDataStoreAnswersFlow().collectAsState(
        initial = HashMap()
    )
    LaunchedEffect(localLordShipAnswers.value) {
        // restore answers
        viewModel.setAnswersState(localLordShipAnswers.value)
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
    val answer9 = answers["9"] ?: ""
    val answer10 = answers["10"] ?: ""
    val answer11 = answers["11"] ?: ""
    val answer12 = answers["12"] ?: ""
    val answer13 = answers["13"] ?: ""

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
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_1))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_2_part_1))
            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_2_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_3_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer2
            ) {
                viewModel.updateAnswerState(key = "2", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_3_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer3
            ) {
                viewModel.updateAnswerState(key = "3", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_3_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer4,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "4", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_4_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer5
            ) {
                viewModel.updateAnswerState(key = "5", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_4_part_2))
            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_4_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer7
            ) {
                viewModel.updateAnswerState(key = "7", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))

            ContentMarkdown(markdown = stringResource(R.string.lordship_page_5_part_1))
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
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_5_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer9
            ) {
                viewModel.updateAnswerState(key = "9", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_6_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer10
            ) {
                viewModel.updateAnswerState(key = "10", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_6_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer11,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "11", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_7_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer12
            ) {
                viewModel.updateAnswerState(key = "12", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_7_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer13,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "13", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.lordship_page_7_part_3))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
