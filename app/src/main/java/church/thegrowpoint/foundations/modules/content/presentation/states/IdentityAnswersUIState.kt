package church.thegrowpoint.foundations.modules.content.presentation.states

data class IdentityAnswersUIState(
    val whatOthersThinkOfMe: Boolean = false,
    val myWeaknesses: Boolean = false,
    val myGradePointAverage: Boolean = false,
    val myPhysicalStrengths: Boolean = false,
    val whatILookInTheMirror: Boolean = false,
    val myFriendsIHangoutWith: Boolean = false,
    val myTalentsAndAbilities: Boolean = false,
    val myIntelligence: Boolean = false,
    val whatMyFamilySaysAboutMe: Boolean = false,
    val myFamilyReputation: Boolean = false,
    val myAttitude: Boolean = false,
    val whatGodSaysAboutMe: Boolean = false,
    val theClothsIWear: Boolean = false,
    val others: Boolean = false,
    override val answers: HashMap<String, String> = HashMap()
) : AnswersUIState(answers)
