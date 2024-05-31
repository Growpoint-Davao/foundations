package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import church.thegrowpoint.foundations.modules.BaseViewModel
import church.thegrowpoint.foundations.modules.content.presentation.states.AnswersUIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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
    protected val dispatcher: CoroutineDispatcher
): BaseViewModel(context) {
    // ui state
    protected abstract val mutableUIState: MutableStateFlow<T>
    abstract val uiState: StateFlow<T>

    /**
     * Updates the data store answers.
     *
     * @param answers The answers to update the data store with.
     */
    protected abstract fun  updateDataStoreAnswers(answers: HashMap<String, String>)

    /**
     * Creates a new state copy with the given answers.
     *
     * @param currentState The current state.
     * @param answers The answers to update the state with.
     * @return The new state copy.
     */
    protected abstract fun createStateCopy(currentState: T, answers: HashMap<String, String>): T

    /**
     * Gets the data store answers flow.
     *
     * @return The data store answers flow.
     */
    abstract fun getDataStoreAnswersFlow(): Flow<HashMap<String, String>>

    /**
     * Sets the answer for the given key.
     *
     * @param key The key of the answer.
     * @param answer The answer to set.
     */
    fun setAnswer(key: String, answer: String) {
        mutableUIState.update { currentState ->
            val latestAnswers = HashMap(currentState.answers)
            latestAnswers[key] = answer

            // update the local data store
            updateDataStoreAnswers(latestAnswers)

            createStateCopy(currentState, latestAnswers)
        }
    }

    /**
     * Sets the answers
     *
     * @param answers The answers to set. It is a key-value pair where the key is the identifier of the answer and the value is the answer.
     */
    fun setAnswers(answers: HashMap<String, String>) {
        // only change the salvation answer state if there are changes in values
        if (answers != uiState.value) {
            mutableUIState.update { currentState ->
                createStateCopy(currentState, answers)
            }
        }
    }
}
