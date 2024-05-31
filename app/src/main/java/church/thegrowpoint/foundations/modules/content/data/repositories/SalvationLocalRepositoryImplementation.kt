package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.BaseContentLocalDataSource
import church.thegrowpoint.foundations.modules.content.domain.repositories.SalvationFlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # SalvationLocalRepositoryImplementation
 *
 * The implementation for local salvation data source interface.
 */
class SalvationLocalRepositoryImplementation @Inject constructor(
    private val localDataSource: BaseContentLocalDataSource
) : SalvationFlowRepository {
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
