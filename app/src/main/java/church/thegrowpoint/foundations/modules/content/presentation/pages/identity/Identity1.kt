package church.thegrowpoint.foundations.modules.content.presentation.pages.identity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.ui.composables.CheckboxWithText
import church.thegrowpoint.foundations.ui.composables.LabeledWithSupportTextOutlinedTextField

@Composable
fun Identity1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    viewModel: IdentityViewModel
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
        viewModel.setBooleanAnswerState("others", othersAnswer.value)
    }

    val identityUIState = viewModel.uiState.collectAsState().value
    val answers = identityUIState.answers
    val othersAnswers = answers["others"] ?: ""

    LazyColumn(
        modifier = Modifier.imePadding(),
        state = state
    ) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.identity_page_1_part_1),
                modifier = modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
            Column(
                modifier = modifier.padding(
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_1),
                    checked = identityUIState.whatOthersThinkOfMe
                ) {
                    viewModel.setBooleanAnswerState("whatOthersThinkOfMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_2),
                    checked = identityUIState.myWeaknesses
                ) {
                    viewModel.setBooleanAnswerState("myWeaknesses", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_3),
                    checked = identityUIState.myGradePointAverage
                ) {
                    viewModel.setBooleanAnswerState("myGradePointAverage", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_4),
                    checked = identityUIState.myPhysicalStrengths
                ) {
                    viewModel.setBooleanAnswerState("myPhysicalStrengths", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_5),
                    checked = identityUIState.whatILookInTheMirror
                ) {
                    viewModel.setBooleanAnswerState("whatILookInTheMirror", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_6),
                    checked = identityUIState.myFriendsIHangoutWith
                ) {
                    viewModel.setBooleanAnswerState("myFriendsIHangoutWith", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_7),
                    checked = identityUIState.myTalentsAndAbilities
                ) {
                    viewModel.setBooleanAnswerState("myTalentsAndAbilities", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_8),
                    checked = identityUIState.myIntelligence
                ) {
                    viewModel.setBooleanAnswerState("myIntelligence", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_9),
                    checked = identityUIState.whatMyFamilySaysAboutMe
                ) {
                    viewModel.setBooleanAnswerState("whatMyFamilySaysAboutMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_10),
                    checked = identityUIState.myFamilyReputation
                ) {
                    viewModel.setBooleanAnswerState("myFamilyReputation", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_11),
                    checked = identityUIState.myAttitude
                ) {
                    viewModel.setBooleanAnswerState("myAttitude", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_12),
                    checked = identityUIState.whatGodSaysAboutMe
                ) {
                    viewModel.setBooleanAnswerState("whatGodSaysAboutMe", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_13),
                    checked = identityUIState.theClothsIWear
                ) {
                    viewModel.setBooleanAnswerState("theClothsIWear", it, true)
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_14),
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
                        viewModel.updateAnswerState(key = "others", answer = it)
                    }
                }
                ContentMarkdown(
                    markdown = stringResource(R.string.identity_page_1_part_2),
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
}
