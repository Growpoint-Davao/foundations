package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Identity
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentBooleanAnswerDataStoreFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreBooleanAnswer
import church.thegrowpoint.foundations.modules.content.presentation.states.IdentityAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class IdentityViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Identity getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Identity setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    @Identity getContentBooleanAnswerDataStoreFlowUseCase: GetContentBooleanAnswerDataStoreFlow,
    @Identity setContentDataStoreBooleanAnswerUseCase: SetContentDataStoreBooleanAnswer,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<IdentityAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(IdentityAnswersUIState())
    override val uiState: StateFlow<IdentityAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: IdentityAnswersUIState,
        answers: HashMap<String, String>
    ): IdentityAnswersUIState {
        return currentState.copy(answers = answers)
    }
}
