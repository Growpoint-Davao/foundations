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
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.ContentMarkdown
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.ui.composables.CheckboxWithText
import church.thegrowpoint.foundations.ui.composables.LabeledWithSupportTextOutlinedTextField

@Composable
fun Identity1(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
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
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
