package church.thegrowpoint.foundations.modules.content.presentation.pages.devotion

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.ui.composables.MultilineLabeledWithSupportTextOutlinedTextField

@Composable
fun Devotion5(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    var textField1 by rememberSaveable { mutableStateOf("") }
    var textField2 by rememberSaveable { mutableStateOf("") }

    LazyColumn(state = state) {
        item {
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_5_part_1),
                modifier = modifier.padding(
                    top = 24.dp,
                    bottom = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
            MultilineLabeledWithSupportTextOutlinedTextField(
                label = "",
                supportText = ""
            ) {
                textField1 = it
            }
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_5_part_2),
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
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            ) {
                textField2 = it
            }
            ContentMarkdown(
                markdown = stringResource(R.string.devotion_page_5_part_3),
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