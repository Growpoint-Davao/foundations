package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # SetDataStoreSalvationAnswers
 *
 * Sets or updates the salvation answers in data store.
 *
 * @property contentDataSourceFlowRepository the salvation data store repository instance.
 */
class SetDataStoreSalvationAnswers @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) : BaseSetContentAnswersDataStoreUseCase(contentDataSourceFlowRepository)
