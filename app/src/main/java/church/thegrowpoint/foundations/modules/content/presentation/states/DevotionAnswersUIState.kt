package church.thegrowpoint.foundations.modules.content.presentation.states

data class DevotionAnswersUIState (
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
