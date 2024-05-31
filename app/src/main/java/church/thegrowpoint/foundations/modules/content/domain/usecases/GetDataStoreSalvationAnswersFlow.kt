package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # GetDataStoreSalvationAnswersFlow
 *
 * The use case fo retrieving salvation content answers from data store.
 *
 * @property contentDataSourceFlowRepository the salvation data store repository instance.
 */
class GetDataStoreSalvationAnswersFlow @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) : BaseGetContentAnswersDataStoreFlowUseCase(contentDataSourceFlowRepository)
