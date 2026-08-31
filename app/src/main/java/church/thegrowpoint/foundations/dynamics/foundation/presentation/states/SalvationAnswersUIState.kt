package church.thegrowpoint.foundations.dynamics.foundation.presentation.states

data class SalvationAnswersUIState(
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
