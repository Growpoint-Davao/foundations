package church.thegrowpoint.foundations.modules.content.presentation.states

data class DiscipleshipAnswersUIState(
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
