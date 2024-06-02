package church.thegrowpoint.foundations.modules.content.domain.repositories

import kotlinx.coroutines.flow.Flow

/**
 * # ContentDataSourceFlowRepository
 *
 * The interface for salvation flow repository.
 */
interface ContentDataSourceFlowRepository {
    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    fun getBooleanAnswerFlow(key: String): Flow<Boolean>

    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    suspend fun setBooleanAnswer(key: String, status: Boolean)

    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    fun getAnswersFlow(): Flow<HashMap<String, String>>

    /**
     * Set answers (which is key value pair of answers)
     */
    suspend fun setAnswers(answers: HashMap<String, String>)
}
