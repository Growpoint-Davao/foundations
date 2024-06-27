package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRemoteRepository
import javax.inject.Inject

class GetContentRemoteAnswers @Inject constructor(
    private val contentRemoteRepository: ContentRemoteRepository
) {
    suspend operator fun invoke(): Map<String, Any?>? {
        return contentRemoteRepository.read()
    }
}
