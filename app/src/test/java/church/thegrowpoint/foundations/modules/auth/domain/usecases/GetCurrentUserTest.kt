package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertSame
import org.junit.Test

class GetCurrentUserTest {
    /**
     * Test getCurrentUser if it will return a user when there is current user.
     */
    @Test
    fun getCurrentUser_shouldReturnAUserWhenThereIsCurrentUser() {
        val mockedAuthRepository = mockk<AuthRepository>()
        val mockedUser = mockk<User>()

        every { mockedAuthRepository.currentUser } returns mockedUser

        val getCurrentUser = GetCurrentUser(mockedAuthRepository)

        assertSame(mockedUser, getCurrentUser())
    }

    /**
     * Test getCurrentUser if it will return a null user when there is no current user.
     */
    @Test
    fun getCurrentUser_shouldReturnANullUserWhenThereIsNoCurrentUser() {
        val mockedAuthRepository = mockk<AuthRepository>()

        every { mockedAuthRepository.currentUser } returns null

        val getCurrentUser = GetCurrentUser(mockedAuthRepository)

        assertSame(null, getCurrentUser())
    }
}
