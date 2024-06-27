package church.thegrowpoint.foundations.modules.content.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import church.thegrowpoint.foundations.modules.Identity
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentBooleanAnswerDataStoreFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreBooleanAnswer
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentRemoteAnswers
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
    @Identity setContentRemoteAnswersUseCase: SetContentRemoteAnswers? = null,
    @Identity getContentRemoteAnswersUseCase: GetContentRemoteAnswers? = null,
    dispatcher: CoroutineDispatcher
): BasePageViewModel<IdentityAnswersUIState>(
    context = context,
    getContentAnswersDataStoreFlowUseCase = getContentAnswersDataStoreFlowUseCase,
    setContentAnswersDataStoreUseCase = setContentAnswersDataStoreUseCase,
    setContentRemoteAnswersUseCase = setContentRemoteAnswersUseCase,
    getContentRemoteAnswersUseCase = getContentRemoteAnswersUseCase,
    dispatcher = dispatcher
) {
    // ui state
    override val mutableUIState = MutableStateFlow(IdentityAnswersUIState())
    override val uiState: StateFlow<IdentityAnswersUIState> = mutableUIState.asStateFlow()

    override fun convertStateToMap(): Map<String, Any>? {
        if (uiState.value.answers.isEmpty() && areIdentitiesFalse()) {
            return null
        }

        val answersData = HashMap<String, Any>()

        answersData.putAll(uiState.value.answers)

        answersData["whatOthersThinkOfMe"] = uiState.value.whatOthersThinkOfMe
        answersData["myWeaknesses"] = uiState.value.myWeaknesses
        answersData["myGradePointAverage"] = uiState.value.myGradePointAverage
        answersData["myPhysicalStrengths"] = uiState.value.myPhysicalStrengths
        answersData["whatILookInTheMirror"] = uiState.value.whatILookInTheMirror
        answersData["myFriendsIHangoutWith"] = uiState.value.myFriendsIHangoutWith
        answersData["myTalentsAndAbilities"] = uiState.value.myTalentsAndAbilities
        answersData["myIntelligence"] = uiState.value.myIntelligence
        answersData["whatMyFamilySaysAboutMe"] = uiState.value.whatMyFamilySaysAboutMe
        answersData["myFamilyReputation"] = uiState.value.myFamilyReputation
        answersData["myAttitude"] = uiState.value.myAttitude
        answersData["whatGodSaysAboutMe"] = uiState.value.whatGodSaysAboutMe
        answersData["theClothsIWear"] = uiState.value.theClothsIWear
        answersData["others"] = uiState.value.others

        return answersData
    }

    private fun areIdentitiesFalse(): Boolean {
        return !uiState.value.whatOthersThinkOfMe && !uiState.value.myWeaknesses
                && !uiState.value.myGradePointAverage && !uiState.value.myPhysicalStrengths
                && !uiState.value.whatILookInTheMirror && !uiState.value.myFriendsIHangoutWith
                && !uiState.value.myTalentsAndAbilities && !uiState.value.myIntelligence
                && !uiState.value.whatMyFamilySaysAboutMe && !uiState.value.myFamilyReputation
                && !uiState.value.myAttitude && !uiState.value.whatGodSaysAboutMe
                && !uiState.value.theClothsIWear && !uiState.value.others
    }

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

    override fun restoreRemoteAnswers(data: Map<String, Any?>) {
        for ((key, value) in data) {
            if (value is Boolean) {
                setBooleanAnswerState(key = key, status = value, updateDataStore = true)
            } else {
                updateAnswerState(key = key, answer = value.toString())
            }
        }
    }
}
