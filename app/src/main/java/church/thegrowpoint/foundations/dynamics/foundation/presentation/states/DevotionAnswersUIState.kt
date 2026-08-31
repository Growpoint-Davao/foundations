package church.thegrowpoint.foundations.dynamics.foundation.presentation.states

data class DevotionAnswersUIState (
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
