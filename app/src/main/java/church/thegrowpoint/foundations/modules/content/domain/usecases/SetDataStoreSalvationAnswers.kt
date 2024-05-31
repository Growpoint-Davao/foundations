package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.SalvationFlowRepository
import javax.inject.Inject

/**
 * # SetDataStoreSalvationAnswers
 *
 * Sets or updates the salvation answers in data store.
 *
 * @property salvationRepository the salvation data store repository instance.
 */
class SetDataStoreSalvationAnswers @Inject constructor(
    private val salvationRepository: SalvationFlowRepository
) {
    /**
     * Set answers (which is key value pair of answers)
     */
    suspend operator fun invoke(answers: HashMap<String, String>) {
        return salvationRepository.setAnswers(answers)
    }
}
