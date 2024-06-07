package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Church
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.ChurchAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChurchViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Church getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Church setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<ChurchAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(ChurchAnswersUIState())
    override val uiState: StateFlow<ChurchAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: ChurchAnswersUIState,
        answers: HashMap<String, String>
    ): ChurchAnswersUIState {
        if (currentState.answers != answers) {
            return currentState.copy(answers = answers) // not the same so create new copy
        }

        return currentState // the same so return the original state
    }
}
