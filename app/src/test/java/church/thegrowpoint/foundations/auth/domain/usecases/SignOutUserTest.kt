package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertSame
import org.junit.Test

class SignOutUserTest {
    @Test
    fun `Test if signs out user`() {
        val mockedAuthRepository = mockk<AuthRepository>()
        every { mockedAuthRepository.signOut() } returns Unit

        val signOutUser = SignOutUser(mockedAuthRepository)

        assertSame(Unit, signOutUser())
    }
}
