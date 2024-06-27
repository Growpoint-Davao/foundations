package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.ContentLocalDataSource
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import kotlinx.coroutines.flow.Flow

/**
 * # LocalDataSourceFlowRepository
 *
 * The class for all data store flow repositories.
 *
 * @property localDataSource the local data source or typically the data store that uses data store.
 */
class LocalDataSourceFlowRepository (
    private val localDataSource: ContentLocalDataSource
) : ContentDataSourceFlowRepository {
    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    override fun getBooleanAnswerFlow(key: String): Flow<Boolean> {
        return localDataSource.getBooleanAnswerFlow(key)
    }

    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    override suspend fun setBooleanAnswer(key: String, status: Boolean) {
        return localDataSource.setBooleanAnswer(key, status)
    }

    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    override fun getAnswersFlow(): Flow<HashMap<String, String>> {
        return localDataSource.getAnswersFlow()
    }

    /**
     * Set answers (which is key value pair of answers)
     */
    override suspend fun setAnswers(answers: Map<String, String>) {
        return localDataSource.setAnswers(answers)
    }
}
