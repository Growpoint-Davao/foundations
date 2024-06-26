package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Church
import church.thegrowpoint.foundations.modules.Devotion
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.DevotionAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DevotionViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Devotion getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Devotion setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    @Devotion setContentRemoteAnswersUseCase: SetContentRemoteAnswers? = null,
    @Devotion getContentRemoteAnswersUseCase: GetContentRemoteAnswers? = null,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<DevotionAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    setContentRemoteAnswersUseCase = setContentRemoteAnswersUseCase,
    getContentRemoteAnswersUseCase = getContentRemoteAnswersUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(DevotionAnswersUIState())
    override val uiState: StateFlow<DevotionAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: DevotionAnswersUIState,
        answers: HashMap<String, String>
    ): DevotionAnswersUIState {
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
