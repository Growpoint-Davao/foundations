package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetDataStoreLordshipAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetDataStoreLordShipAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.LordshipAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LordshipViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getDataStoreLordshipAnswersFlowUseCase: GetDataStoreLordshipAnswersFlow,
    private val setDataStoreLordShipAnswersUseCase: SetDataStoreLordShipAnswers,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<LordshipAnswersUIState>(context, dispatcher) {
    // ui state
    override val mutableUIState = MutableStateFlow(LordshipAnswersUIState())
    override val uiState: StateFlow<LordshipAnswersUIState> = mutableUIState.asStateFlow()

    override fun getDataStoreAnswersFlow(): Flow<HashMap<String, String>> {
        return getDataStoreLordshipAnswersFlowUseCase()
    }

    override fun updateDataStoreAnswers(answers: HashMap<String, String>) {
        viewModelScope.launch(dispatcher) {
            setDataStoreLordShipAnswersUseCase(answers)
        }
    }

    override fun createStateCopy(
        currentState: LordshipAnswersUIState,
        answers: HashMap<String, String>
    ): LordshipAnswersUIState {
        return currentState.copy(answers = answers)
    }
}
