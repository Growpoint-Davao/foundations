package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # SetContentDataStoreBooleanAnswer
 *
 * The use case for setting content boolean answer to data store.
 *
 * @property contentDataSourceFlowRepository the content data source repository.
 */
class SetContentDataStoreBooleanAnswer @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) {
    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    suspend operator fun invoke(key: String, status: Boolean) {
        return contentDataSourceFlowRepository.setBooleanAnswer(key, status)
    }
}
