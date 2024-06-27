package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # SetContentDataStoreAnswers
 *
 * The base use case class for setting the answers through data store.
 *
 * @property contentDataSourceFlowRepository the content data store repository instance.
 */
class SetContentDataStoreAnswers @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) {
    /**
     * Set answers (which is key value pair of answers)
     */
    suspend operator fun invoke(answers: Map<String, String>) {
        return contentDataSourceFlowRepository.setAnswers(answers)
    }
}
