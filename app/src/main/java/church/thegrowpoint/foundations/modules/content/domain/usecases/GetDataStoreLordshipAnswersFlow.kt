package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # GetDataStoreLordshipAnswersFlow
 *
 * The use case fo retrieving lordship content answers from data store.
 *
 * @property contentDataSourceFlowRepository the lordship data store repository instance.
 */
class GetDataStoreLordshipAnswersFlow @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) : BaseGetContentAnswersDataStoreFlowUseCase(contentDataSourceFlowRepository)
