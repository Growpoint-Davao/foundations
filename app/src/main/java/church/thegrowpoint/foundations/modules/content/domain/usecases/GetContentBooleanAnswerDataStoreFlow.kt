package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # GetContentBooleanAnswerDataStoreFlow
 *
 * The use case for retrieving content boolean answer flow from data store.
 *
 * @property contentDataSourceFlowRepository the content data source repository.
 */
class GetContentBooleanAnswerDataStoreFlow @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) {
    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    operator fun invoke(key: String): Flow<Boolean> {
        return contentDataSourceFlowRepository.getBooleanAnswerFlow(key)
    }
}
