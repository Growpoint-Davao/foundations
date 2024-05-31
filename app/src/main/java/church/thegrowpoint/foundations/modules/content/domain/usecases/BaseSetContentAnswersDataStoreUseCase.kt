package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository

/**
 * # BaseSetContentAnswersDataStoreUseCase
 *
 * The base use case class for setting the answers through data store.
 *
 * @property contentDataSourceFlowRepository the content data store repository instance.
 */
abstract class BaseSetContentAnswersDataStoreUseCase(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) {
    /**
     * Set answers (which is key value pair of answers)
     */
    suspend operator fun invoke(answers: HashMap<String, String>) {
        return contentDataSourceFlowRepository.setAnswers(answers)
    }
}
