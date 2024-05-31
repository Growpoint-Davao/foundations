package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.BaseContentLocalDataSource
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import kotlinx.coroutines.flow.Flow

/**
 * # LocalDataSourceFlowRepository
 *
 * The base class for all data store flow repositories.
 *
 * @property localDataSource the local data source or typically the data store that uses data store.
 */
abstract class LocalDataSourceFlowRepository (
    private val localDataSource: BaseContentLocalDataSource
) : ContentDataSourceFlowRepository {
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
    override suspend fun setAnswers(answers: HashMap<String, String>) {
        return localDataSource.setAnswers(answers)
    }
}
