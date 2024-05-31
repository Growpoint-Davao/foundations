package church.thegrowpoint.foundations.modules.content.presentation.states

data class SalvationAnswersUIState(
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
