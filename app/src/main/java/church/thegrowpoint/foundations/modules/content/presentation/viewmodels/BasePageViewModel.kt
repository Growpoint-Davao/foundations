package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.presentation.states.AnswersUIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * # BasePageViewModel
 *
 * The base view model for all pages in the app.
 *
 * @property appContext The application context.
 * @property dispatcher The coroutine dispatcher to use.
 */
abstract class BasePageViewModel<T : AnswersUIState>(
    context: Context,
    private val getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    private val setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    private val setContentRemoteAnswersUseCase: SetContentRemoteAnswers? = null,
    private val getContentRemoteAnswersUseCase: GetContentRemoteAnswers? = null,
    protected val dispatcher: CoroutineDispatcher
): BaseViewModel(context) {
    // ui state
    protected abstract val mutableUIState: MutableStateFlow<T>
    abstract val uiState: StateFlow<T>

    /**
     * Gets the data store answers flow.
     *
     * @return The data store answers flow.
     */
    fun getDataStoreAnswersFlow(): Flow<HashMap<String, String>> {
        return getContentAnswersDataStoreFlowUseCase()
    }

    /**
     * Updates the data store answers.
     *
     * @param answers The answers to update the data store with.
     */
    private fun updateDataStoreAnswers(answers: HashMap<String, String>) {
        viewModelScope.launch(dispatcher) {
            setContentAnswersDataStoreUseCase(answers)
        }
    }

    /**
     * Creates a new state copy with the given answers.
     *
     * @param currentState The current state.
     * @param answers The answers to update the state with.
     * @return The new state copy.
     */
    protected abstract fun createStateCopy(currentState: T, answers: HashMap<String, String>): T

    protected abstract fun restoreRemoteAnswers(data: Map<String, Any?>)

    protected open fun convertStateToMap(): Map<String, Any>? {
        if (uiState.value.answers.isNotEmpty()) {
            return uiState.value.answers
        }

        return null
    }

    /**
     * Sets the answer for the given key. This will also update the data store.
     *
     * @param key The key of the answer.
     * @param answer The answer to set.
     * @param updateDataStore Whether to update the data store with the new answer.
     */
    fun updateAnswerState(key: String, answer: String, updateDataStore: Boolean = true) {
        mutableUIState.update { currentState ->
            val latestAnswers = HashMap(currentState.answers)
            latestAnswers[key] = answer

            // update the local data store
            if (updateDataStore) {
                updateDataStoreAnswers(latestAnswers)
            }

            createStateCopy(currentState, latestAnswers)
        }
    }

    /**
     * Sets the answers
     *
     * @param answers The answers to set. It is a key-value pair where the key is the identifier of the answer and the value is the answer.
     */
    fun setAnswersState(answers: HashMap<String, String>) {
        // only change the salvation answer state if there are changes in values
        if (answers != uiState.value) {
            mutableUIState.update { currentState ->
                createStateCopy(currentState, answers)
            }
        }
    }

    fun saveAnswersToRemoteSource() {
        viewModelScope.launch(dispatcher) {
            setContentRemoteAnswersUseCase?.let {
                val data = convertStateToMap()
                if (data != null) {
                    it(data)
                }
            }
        }
    }

    fun restoreAnswersFromRemoteResource() {
        viewModelScope.launch(dispatcher) {
            getContentRemoteAnswersUseCase?.let {
                val data = it()
                if (data != null) {
                    restoreRemoteAnswers(data)
                }
            }
        }
    }
}
