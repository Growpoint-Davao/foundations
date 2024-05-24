package church.thegrowpoint.foundations.modules.auth.domain.usecases

import church.thegrowpoint.foundations.modules.auth.data.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import com.google.firebase.auth.FirebaseUser
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RegisterUserTest {
    @Test
    fun register_shouldBeAbleToRegisterUserWithEmailAndPassword() = runTest {
        val mockedAuthRepository = mockk<AuthRepository>()
        val mockedFirebaseUser = mockk<FirebaseUser>()

        val testEmail = "foo@bar.com"
        val testPassword = "112233"

        every { mockedFirebaseUser.uid } returns "112233445566"
        every { mockedFirebaseUser.email } returns testEmail
        every { mockedFirebaseUser.displayName } returns "Foo"
        val userResult = AuthRepository.UserResult(
            user = User(mockedFirebaseUser)
        )
        coEvery { mockedAuthRepository.register(email = testEmail, password = testPassword) } returns userResult

        val registerUser = RegisterUser(mockedAuthRepository)
        val result = registerUser(email = testEmail, password = testPassword)

        assertEquals("foo@bar.com", result!!.user!!.email)
        assertEquals("Foo", result.user!!.name)
    }

    @Test
    fun register_canRetrieveNullUserResult() = runTest {
        val mockedAuthRepository = mockk<AuthRepository>()

        val testEmail = "foo@bar.com"
        val testPassword = "112233"

        coEvery { mockedAuthRepository.register(email = testEmail, password = testPassword) } returns null

        val registerUser = RegisterUser(mockedAuthRepository)
        val result = registerUser(email = testEmail, password = testPassword)

        assertNull(result)
    }
}