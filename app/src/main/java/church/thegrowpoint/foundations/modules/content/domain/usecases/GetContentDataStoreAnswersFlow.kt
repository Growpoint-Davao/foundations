package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # GetContentDataStoreAnswersFlow
 *
 * The base use case for content data store.
 *
 * @property contentDataSourceFlowRepository the content data source repository.
 */
class GetContentDataStoreAnswersFlow @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) {
    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    operator fun invoke(): Flow<HashMap<String, String>> {
        return contentDataSourceFlowRepository.getAnswersFlow()
    }
}
