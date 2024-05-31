package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.SalvationFlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # GetDataStoreSalvationAnswers
 *
 * The use case fo retrieving salvation content answers from data store.
 *
 * @property salvationRepository the salvation data store repository instance.
 */
class GetDataStoreSalvationAnswers @Inject constructor(
    private val salvationRepository: SalvationFlowRepository
) {
    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    operator fun invoke(): Flow<HashMap<String, String>> {
        return salvationRepository.getAnswersFlow()
    }
}
