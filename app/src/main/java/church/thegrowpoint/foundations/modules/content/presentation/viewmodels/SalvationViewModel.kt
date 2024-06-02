package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.Salvation
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.SalvationAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * # SalvationViewModel
 *
 * The view model for salvation content pages.
 *
 * @property appContext The application context.
 */
@HiltViewModel
class SalvationViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Salvation getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Salvation setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    dispatcher: CoroutineDispatcher
) : BasePageViewModel<SalvationAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(SalvationAnswersUIState())
    override val uiState: StateFlow<SalvationAnswersUIState> = mutableUIState.asStateFlow()

    override fun createStateCopy(
        currentState: SalvationAnswersUIState,
        answers: HashMap<String, String>
    ): SalvationAnswersUIState {
        if (currentState.answers != answers) {
            return currentState.copy(answers = answers) // not the same so create new copy
        }

        return currentState // the same so return the original state
    }
}
