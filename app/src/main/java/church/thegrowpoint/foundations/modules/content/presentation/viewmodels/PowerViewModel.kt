package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Power
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.PowerAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PowerViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Power getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Power setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    @Power setContentRemoteAnswersUseCase: SetContentRemoteAnswers? = null,
    @Power getContentRemoteAnswersUseCase: GetContentRemoteAnswers? = null,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<PowerAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    setContentRemoteAnswersUseCase = setContentRemoteAnswersUseCase,
    getContentRemoteAnswersUseCase = getContentRemoteAnswersUseCase,
    dispatcher = dispatcher
) {
    override val mutableUIState = MutableStateFlow(PowerAnswersUIState())
    override val uiState: StateFlow<PowerAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: PowerAnswersUIState,
        answers: HashMap<String, String>
    ): PowerAnswersUIState {
        if (currentState.answers != answers) {
            return currentState.copy(answers = answers) // not the same so create new copy
        }

        return currentState // the same so return the original state
    }

    override fun restoreRemoteAnswers(data: Map<String, Any?>) {
        for ((key, value) in data) {
            updateAnswerState(key = key, answer = value.toString())
        }
    }
}
