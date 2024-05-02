package church.thegrowpoint.foundations.auth.domain.usecases

import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertSame
import org.junit.Test

class SignOutUserTest {
    /**
     * The repository signOut should be called when SignOutUser is called.
     */
    @Test
    fun signOutUser_authRepositoryCalledIsCalledDuringSignOutCall() {
        val mockedAuthRepository = mockk<AuthRepository>()
        every { mockedAuthRepository.signOut() } returns Unit

        val signOutUser = SignOutUser(mockedAuthRepository)

        assertSame(Unit, signOutUser())
        verify(exactly = 1) { mockedAuthRepository.signOut() }
    }
}
