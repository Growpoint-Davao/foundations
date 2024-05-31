package church.thegrowpoint.foundations.modules.content.domain.repositories

import kotlinx.coroutines.flow.Flow

/**
 * # SalvationFlowRepository
 *
 * The interface for salvation flow repository.
 */
interface SalvationFlowRepository {
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
