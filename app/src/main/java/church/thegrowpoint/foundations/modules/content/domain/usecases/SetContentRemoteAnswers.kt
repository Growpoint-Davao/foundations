package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRemoteRepository
import javax.inject.Inject

/**
 * # SetContentRemoteAnswers
 *
 * The use case for setting remote content answers.
 *
 * @property contentRemoteRepository The content remote repository.
 */
class SetContentRemoteAnswers @Inject constructor(
    private val contentRemoteRepository: ContentRemoteRepository
) {
    suspend operator fun invoke(data: Map<String, Any?>): Any? {
        return contentRemoteRepository.write(data)
    }
}
