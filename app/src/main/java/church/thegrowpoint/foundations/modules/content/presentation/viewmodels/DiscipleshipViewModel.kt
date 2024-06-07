package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Discipleship
import church.thegrowpoint.foundations.modules.Salvation
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.DiscipleshipAnswersUIState
import church.thegrowpoint.foundations.modules.content.presentation.states.SalvationAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DiscipleshipViewModel@Inject constructor(
    @ApplicationContext context: Context,
    @Discipleship getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Discipleship setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    dispatcher: CoroutineDispatcher
) : BasePageViewModel<DiscipleshipAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(DiscipleshipAnswersUIState())
    override val uiState: StateFlow<DiscipleshipAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: DiscipleshipAnswersUIState,
        answers: HashMap<String, String>
    ): DiscipleshipAnswersUIState {
        if (currentState.answers != answers) {
            return currentState.copy(answers = answers) // not the same so create new copy
        }

        return currentState // the same so return the original state
    }
}
