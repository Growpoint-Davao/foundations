package church.thegrowpoint.foundations.dynamics.foundation.presentation.states

data class DiscipleshipAnswersUIState(
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
