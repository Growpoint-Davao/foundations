package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetDataStoreSalvationAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetDataStoreSalvationAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.LordshipAnswersUIState
import church.thegrowpoint.foundations.modules.content.presentation.states.SalvationAnswersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val getDataStoreSalvationAnswersUseCase: GetDataStoreSalvationAnswersFlow,
    private val setSalvationAnswersUseCase: SetDataStoreSalvationAnswers,
    dispatcher: CoroutineDispatcher
) : BasePageViewModel<SalvationAnswersUIState>(context, dispatcher) {
    // ui state
    override val mutableUIState = MutableStateFlow(SalvationAnswersUIState())
    override val uiState: StateFlow<SalvationAnswersUIState> = mutableUIState.asStateFlow()

    // TODO: test these functions
    override fun getDataStoreAnswersFlow(): Flow<HashMap<String, String>> {
        return getDataStoreSalvationAnswersUseCase()
    }

    override fun updateDataStoreAnswers(answers: HashMap<String, String>) {
        viewModelScope.launch(dispatcher) {
            setSalvationAnswersUseCase(answers)
        }
    }

    override fun createStateCopy(
        currentState: SalvationAnswersUIState,
        answers: HashMap<String, String>
    ): SalvationAnswersUIState {
        return currentState.copy(answers = answers)
    }
}
