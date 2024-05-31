package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import javax.inject.Inject

/**
 * # SetDataStoreLordShipAnswers
 *
 * Sets or updates the lordship answers in data store.
 *
 * @property contentDataSourceFlowRepository the lordship data store repository instance.
 */
class SetDataStoreLordShipAnswers @Inject constructor(
    private val contentDataSourceFlowRepository: ContentDataSourceFlowRepository
) : BaseSetContentAnswersDataStoreUseCase(contentDataSourceFlowRepository)
