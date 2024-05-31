package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetDataStoreSalvationAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetDataStoreSalvationAnswers
import church.thegrowpoint.foundations.modules.content.presentation.SalvationAnswersUIState
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
    private val dispatcher: CoroutineDispatcher
): BaseViewModel(context) {
    // salvation answers state
    private val _salvationAnswersUIState = MutableStateFlow(SalvationAnswersUIState())
    val salvationAnswersUIState: StateFlow<SalvationAnswersUIState> = _salvationAnswersUIState.asStateFlow()

    // TODO: test these functions
    private fun updateDataStoreSalvationAnswers(answers: HashMap<String, String>) {
        viewModelScope.launch(dispatcher) {
            setSalvationAnswersUseCase(answers)
        }
    }

    fun getDataStoreSalvationAnswersFlow(): Flow<HashMap<String, String>> {
        return getDataStoreSalvationAnswersUseCase()
    }

    fun setSalvationAnswer(key: String, answer: String) {
        _salvationAnswersUIState.update { currentState ->
            val latestAnswers = HashMap(currentState.answers)
            latestAnswers[key] = answer

            // update the local data store
            updateDataStoreSalvationAnswers(latestAnswers)

            currentState.copy(answers = latestAnswers)
        }
    }

    fun setSalvationAnswers(answers: HashMap<String, String>) {
        // only change the salvation answer state if there are changes in values
        if (!answers.equals(salvationAnswersUIState.value)) {
            _salvationAnswersUIState.update { currentState ->
                currentState.copy(answers = answers)
            }
        }
    }
}
