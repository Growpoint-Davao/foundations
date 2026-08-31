package church.thegrowpoint.foundations.dynamics.foundation.domain.usecases

import church.thegrowpoint.foundations.dynamics.foundation.domain.repositories.ContentRemoteRepository
import javax.inject.Inject

class GetContentRemoteAnswers @Inject constructor(
    private val contentRemoteRepository: ContentRemoteRepository
) {
    suspend operator fun invoke(): Map<String, Any?>? {
        return contentRemoteRepository.read()
    }
}
