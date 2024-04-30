package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.models.User
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertSame
import org.junit.Test

class GetCurrentUserTest {
    @Test
    fun `Test if retrieves user when there is authenticated user`() {
        val mockedAuthRepository = mockk<AuthRepository>()
        val mockedUser = mockk<User>()

        every { mockedAuthRepository.currentUser } returns mockedUser

        val getCurrentUser = GetCurrentUser(mockedAuthRepository)

        assertSame(mockedUser, getCurrentUser())
    }

    @Test
    fun `Test if returns null when there is not authenticated user`() {
        val mockedAuthRepository = mockk<AuthRepository>()

        every { mockedAuthRepository.currentUser } returns null

        val getCurrentUser = GetCurrentUser(mockedAuthRepository)

        assertSame(null, getCurrentUser())
    }
}
