package church.thegrowpoint.foundations.modules.content.presentation.pages.identity

import androidx.compose.foundation.layout.Column
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
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.ui.composables.CheckboxWithText
import church.thegrowpoint.foundations.ui.composables.LabeledWithSupportTextOutlinedTextField
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Identity(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: IdentityViewModel = hiltViewModel(),
    header: @Composable (() -> Unit)? = null
) {
    val identityAnswers = viewModel.getDataStoreAnswersFlow()
        .collectAsState(
            initial = HashMap()
        )

    val whatOthersThinkAnswer = viewModel.getBooleanAnswerFlow("whatOthersThinkOfMe")
        .collectAsState(
            initial = false
        )
    val myWeaknessesAnswer = viewModel.getBooleanAnswerFlow("myWeaknesses")
        .collectAsState(
            initial = false
        )
    val myGradePointAverageAnswer = viewModel.getBooleanAnswerFlow("myGradePointAverage")
        .collectAsState(
            initial = false
        )
    val myPhysicalStrengthsAnswer = viewModel.getBooleanAnswerFlow("myPhysicalStrengths")
        .collectAsState(
            initial = false
        )
    val whatILookInTheMirrorAnswer = viewModel.getBooleanAnswerFlow("whatILookInTheMirror")
        .collectAsState(
            initial = false
        )
    val myFriendsIHangoutWithAnswer = viewModel.getBooleanAnswerFlow("myFriendsIHangoutWith")
        .collectAsState(
            initial = false
        )
    val myTalentsAndAbilitiesAnswer = viewModel.getBooleanAnswerFlow("myTalentsAndAbilities")
        .collectAsState(
            initial = false
        )
    val myIntelligenceAnswer = viewModel.getBooleanAnswerFlow("myIntelligence")
        .collectAsState(
            initial = false
        )
    val whatMyFamilySaysAboutMeAnswer = viewModel.getBooleanAnswerFlow("whatMyFamilySaysAboutMe")
        .collectAsState(
            initial = false
        )
    val myFamilyReputationAnswer = viewModel.getBooleanAnswerFlow("myFamilyReputation")
        .collectAsState(
            initial = false
        )
    val myAttitudeAnswer = viewModel.getBooleanAnswerFlow("myAttitude")
        .collectAsState(
            initial = false
        )
    val whatGodSaysAboutMeAnswer = viewModel.getBooleanAnswerFlow("whatGodSaysAboutMe")
        .collectAsState(
            initial = false
        )
    val theClothsIWearAnswer = viewModel.getBooleanAnswerFlow("theClothsIWear")
        .collectAsState(
            initial = false
        )
    val othersAnswer = viewModel.getBooleanAnswerFlow("others")
        .collectAsState(
            initial = false
        )

    LaunchedEffect(identityAnswers.value) {
        // restore answers
        viewModel.setAnswersState(identityAnswers.value)
    }

    LaunchedEffect(whatOthersThinkAnswer.value) {
        viewModel.setBooleanAnswerState("whatOthersThinkOfMe", whatOthersThinkAnswer.value)
    }
    LaunchedEffect(myWeaknessesAnswer.value) {
        viewModel.setBooleanAnswerState("myWeaknesses", myWeaknessesAnswer.value)
    }
    LaunchedEffect(myGradePointAverageAnswer.value) {
        viewModel.setBooleanAnswerState("myGradePointAverage", myGradePointAverageAnswer.value)
    }
    LaunchedEffect(myPhysicalStrengthsAnswer.value) {
        viewModel.setBooleanAnswerState("myPhysicalStrengths", myPhysicalStrengthsAnswer.value)
    }
    LaunchedEffect(whatILookInTheMirrorAnswer.value) {
        viewModel.setBooleanAnswerState("whatILookInTheMirror", whatILookInTheMirrorAnswer.value)
    }
    LaunchedEffect(myFriendsIHangoutWithAnswer.value) {
        viewModel.setBooleanAnswerState("myFriendsIHangoutWith", myFriendsIHangoutWithAnswer.value)
    }
    LaunchedEffect(myTalentsAndAbilitiesAnswer.value) {
        viewModel.setBooleanAnswerState("myTalentsAndAbilities", myTalentsAndAbilitiesAnswer.value)
    }
    LaunchedEffect(myIntelligenceAnswer.value) {
        viewModel.setBooleanAnswerState("myIntelligence", myIntelligenceAnswer.value)
    }
    LaunchedEffect(whatMyFamilySaysAboutMeAnswer.value) {
        viewModel.setBooleanAnswerState("whatMyFamilySaysAboutMe", whatMyFamilySaysAboutMeAnswer.value)
    }
    LaunchedEffect(myFamilyReputationAnswer.value) {
        viewModel.setBooleanAnswerState("myFamilyReputation", myFamilyReputationAnswer.value)
    }
    LaunchedEffect(myAttitudeAnswer.value) {
        viewModel.setBooleanAnswerState("myAttitude", myAttitudeAnswer.value)
    }
    LaunchedEffect(whatGodSaysAboutMeAnswer.value) {
        viewModel.setBooleanAnswerState("whatGodSaysAboutMe", whatGodSaysAboutMeAnswer.value)
    }
    LaunchedEffect(theClothsIWearAnswer.value) {
        viewModel.setBooleanAnswerState("theClothsIWear", theClothsIWearAnswer.value)
    }
    LaunchedEffect(othersAnswer.value) {
        viewModel.setBooleanAnswerState("othersAnswer", othersAnswer.value)
    }

    val identityUIState = viewModel.uiState.collectAsState().value
    val answers = identityUIState.answers
    val othersAnswers = answers["othersAnswer"] ?: ""

    val answer1 = answers["1"] ?: ""
    val answer2 = answers["2"] ?: ""
    val answer3 = answers["3"] ?: ""
    val answer4 = answers["4"] ?: ""
    val answer5 = answers["5"] ?: ""
    val answer6 = answers["6"] ?: ""
    val answer8 = answers["8"] ?: ""
    val answer9 = answers["9"] ?: ""
    val answer10 = answers["10"] ?: ""

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
            ContentMarkdown(markdown = stringResource(R.string.identity_page_1_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_2_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            ) {
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_1),
                    checked = identityUIState.whatOthersThinkOfMe
                ) {
                    viewModel.setBooleanAnswerState("whatOthersThinkOfMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_2),
                    checked = identityUIState.myWeaknesses
                ) {
                    viewModel.setBooleanAnswerState("myWeaknesses", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_3),
                    checked = identityUIState.myGradePointAverage
                ) {
                    viewModel.setBooleanAnswerState("myGradePointAverage", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_4),
                    checked = identityUIState.myPhysicalStrengths
                ) {
                    viewModel.setBooleanAnswerState("myPhysicalStrengths", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_5),
                    checked = identityUIState.whatILookInTheMirror
                ) {
                    viewModel.setBooleanAnswerState("whatILookInTheMirror", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_6),
                    checked = identityUIState.myFriendsIHangoutWith
                ) {
                    viewModel.setBooleanAnswerState("myFriendsIHangoutWith", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_7),
                    checked = identityUIState.myTalentsAndAbilities
                ) {
                    viewModel.setBooleanAnswerState("myTalentsAndAbilities", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_8),
                    checked = identityUIState.myIntelligence
                ) {
                    viewModel.setBooleanAnswerState("myIntelligence", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_9),
                    checked = identityUIState.whatMyFamilySaysAboutMe
                ) {
                    viewModel.setBooleanAnswerState("whatMyFamilySaysAboutMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_10),
                    checked = identityUIState.myFamilyReputation
                ) {
                    viewModel.setBooleanAnswerState("myFamilyReputation", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_11),
                    checked = identityUIState.myAttitude
                ) {
                    viewModel.setBooleanAnswerState("myAttitude", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_12),
                    checked = identityUIState.whatGodSaysAboutMe
                ) {
                    viewModel.setBooleanAnswerState("whatGodSaysAboutMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_13),
                    checked = identityUIState.theClothsIWear
                ) {
                    viewModel.setBooleanAnswerState("theClothsIWear", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_2_identity_option_14),
                    checked = identityUIState.others
                ) {
                    viewModel.setBooleanAnswerState("others", it, true)

                    if (!it) {
                        viewModel.updateAnswerState(key = "others", answer = "")
                    }
                }
                if (identityUIState.others) {
                    LabeledWithSupportTextOutlinedTextField(
                        label = "",
                        supportText = "",
                        value = othersAnswers,
                        singleLine = true,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        viewModel.updateAnswerState(key = "othersAnswer", answer = it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                ContentMarkdown(markdown = stringResource(R.string.identity_page_2_part_2))
                Spacer(modifier = Modifier.height(8.dp))
            }

            ContentMarkdown(markdown = stringResource(R.string.identity_page_3_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer1
            ) {
                viewModel.updateAnswerState(key = "1", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_3_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer2
            ) {
                viewModel.updateAnswerState(key = "2", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_3_part_3))
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
            ContentMarkdown(markdown = stringResource(R.string.identity_page_3_part_4))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_4_part_1))
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
            ContentMarkdown(markdown = stringResource(R.string.identity_page_4_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_5_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer5
            ) {
                viewModel.updateAnswerState(key = "5", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_5_part_2))
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
            ContentMarkdown(markdown = stringResource(R.string.identity_page_5_part_3))
            Spacer(modifier = Modifier.height(8.dp))

            ContentMarkdown(markdown = stringResource(R.string.identity_page_6_part_1))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer8
            ) {
                viewModel.updateAnswerState(key = "8", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_6_part_2))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer9
            ) {
                viewModel.updateAnswerState(key = "9", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_6_part_3))
            Spacer(modifier = Modifier.height(8.dp))
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = "",
                value = answer10,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                viewModel.updateAnswerState(key = "10", answer = it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContentMarkdown(markdown = stringResource(R.string.identity_page_7_part_1))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
