package church.thegrowpoint.foundations.dynamics.foundation.presentation.states

data class ChurchAnswersUIState (
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
