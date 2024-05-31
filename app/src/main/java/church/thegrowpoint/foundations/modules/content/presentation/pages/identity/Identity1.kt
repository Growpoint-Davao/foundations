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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.ui.composables.CheckboxWithText
import church.thegrowpoint.foundations.ui.composables.LabeledWithSupportTextOutlinedTextField

@Composable
fun Identity1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    var whatOthersThinkCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var weaknessCheckboxState by rememberSaveable { mutableStateOf(false) }
    var myGradePointAverageCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var myPhysicalStrengthCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var whatILookInMirrorCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var friendsIHangOutWithCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var myTalentsAndAbilitiesCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var myIntelligenceCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var whatMyFamilyThinkCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var myFamilyReputation by rememberSaveable { mutableStateOf(false) }
    var myAttitudeCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var whatGodSaysAboutMeCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var clothesIWearCheckBoxState by rememberSaveable { mutableStateOf(false) }
    var othersCheckBoxState by rememberSaveable { mutableStateOf(false) }

    var othersText by rememberSaveable { mutableStateOf("") }

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
                    checked = whatOthersThinkCheckBoxState
                ) {
                    whatOthersThinkCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_2),
                    checked = weaknessCheckboxState
                ) {
                    weaknessCheckboxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_3),
                    checked = myGradePointAverageCheckBoxState
                ) {
                    myGradePointAverageCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_4),
                    checked = myPhysicalStrengthCheckBoxState
                ) {
                    myPhysicalStrengthCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_5),
                    checked = whatILookInMirrorCheckBoxState
                ) {
                    whatILookInMirrorCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_6),
                    checked = friendsIHangOutWithCheckBoxState
                ) {
                    friendsIHangOutWithCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_7),
                    checked = myTalentsAndAbilitiesCheckBoxState
                ) {
                    myTalentsAndAbilitiesCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_8),
                    checked = myIntelligenceCheckBoxState
                ) {
                    myIntelligenceCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_9),
                    checked = whatMyFamilyThinkCheckBoxState
                ) {
                    whatMyFamilyThinkCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_10),
                    checked = myFamilyReputation
                ) {
                    myFamilyReputation = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_11),
                    checked = myAttitudeCheckBoxState
                ) {
                    myAttitudeCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_12),
                    checked = whatGodSaysAboutMeCheckBoxState
                ) {
                    whatGodSaysAboutMeCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_13),
                    checked = clothesIWearCheckBoxState
                ) {
                    clothesIWearCheckBoxState = it
                }
                CheckboxWithText(
                    label = stringResource(R.string.identity_page_1_identity_option_14),
                    checked = othersCheckBoxState
                ) {
                    othersCheckBoxState = it
                }
                if (othersCheckBoxState) {
                    LabeledWithSupportTextOutlinedTextField(
                        label = "",
                        supportText = "",
                        value = othersText,
                        singleLine = true,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        othersText = it
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
