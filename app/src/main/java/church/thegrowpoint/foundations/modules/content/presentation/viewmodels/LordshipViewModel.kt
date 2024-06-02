package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Lordship
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.LordshipAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LordshipViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Lordship getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Lordship setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<LordshipAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(LordshipAnswersUIState())
    override val uiState: StateFlow<LordshipAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: LordshipAnswersUIState,
        answers: HashMap<String, String>
    ): LordshipAnswersUIState {
        return currentState.copy(answers = answers)
    }
}
