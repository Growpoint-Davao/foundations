package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.Identity
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentBooleanAnswerDataStoreFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreBooleanAnswer
import church.thegrowpoint.foundations.modules.content.presentation.states.IdentityAnswersUIState
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

@HiltViewModel
class IdentityViewModel @Inject constructor(
    @ApplicationContext context: Context,
    @Identity getContentAnswersDataStoreFlowUseCase: GetContentDataStoreAnswersFlow,
    @Identity setContentAnswersDataStoreUseCase: SetContentDataStoreAnswers,
    @Identity private val getContentBooleanAnswerDataStoreFlowUseCase: GetContentBooleanAnswerDataStoreFlow,
    @Identity private val setContentDataStoreBooleanAnswerUseCase: SetContentDataStoreBooleanAnswer,
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

    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    fun getBooleanAnswerFlow(key: String): Flow<Boolean> {
        return getContentBooleanAnswerDataStoreFlowUseCase(key)
    }

    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    private fun setBooleanAnswer(key: String, status: Boolean) {
        viewModelScope.launch(dispatcher) {
            setContentDataStoreBooleanAnswerUseCase(key, status)
        }
    }

    /**
     * Set boolean answer state.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    fun setBooleanAnswerState(key: String, status: Boolean, updateDataStore: Boolean = false) {
        mutableUIState.update { currentState ->
            try {
                val newState = currentState.copy()

                // get the property via reflection
                val field = IdentityAnswersUIState::class.java.getDeclaredField(key)
                field.isAccessible = true

                // set the status
                field.set(newState, status)

                // update the data store
                if (updateDataStore) {
                    setBooleanAnswer(key, status)
                }

                // return the new state
                newState
            } catch (e: Exception) {
                // it could be the field does not exist
                e.printStackTrace()
                currentState// return the original state
            }
        }
    }

    /**
     * Create state copy with new hash map of answers.
     *
     * @param currentState the current UI state.
     * @param answers the new answers.
     */
    override fun createStateCopy(
        currentState: IdentityAnswersUIState,
        answers: HashMap<String, String>
    ): IdentityAnswersUIState {
        if (currentState.answers != answers) {
            return currentState.copy(answers = answers) // not the same so create new copy
        }

        return currentState // the same so return the original state
    }
}
