package church.thegrowpoint.foundations.auth.presentation

import church.thegrowpoint.foundations.auth.AuthModule
import church.thegrowpoint.foundations.auth.domain.models.User
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.auth.domain.usecases.SignOutUser
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test

class AuthViewModelTest {
    private lateinit var getCurrentUser: GetCurrentUser
    private lateinit var signOutUser: SignOutUser
    private lateinit var registerUser: RegisterUser
    private lateinit var signInWithEmailAndPassword: SignInWithEmailAndPassword
    private lateinit var signInWithGoogle: SignInWithGoogle
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // this avoid blocking of coEvery
        // Similar issues:
        // https://github.com/mockk/mockk/issues/766
        // https://github.com/mockk/mockk/issues/344
        mockkStatic("kotlinx.coroutines.tasks.TasksKt")

        getCurrentUser = mockk<GetCurrentUser>()
        signOutUser = mockk<SignOutUser>()
        registerUser = mockk<RegisterUser>()
        signInWithEmailAndPassword = mockk<SignInWithEmailAndPassword>()
        signInWithGoogle = mockk<SignInWithGoogle>()

        every { getCurrentUser() } returns null
    }

    @Test
    fun `setCurrentUser - Test if sets null user during init when no authenticated user`() {
        // mock the current user
        every { getCurrentUser() } returns null

        val viewModel = AuthViewModel(
            getCurrentUser = getCurrentUser,
            signOutUser = signOutUser,
            registerUser = registerUser,
            signInWithEmailAndPassword = signInWithEmailAndPassword,
            signInWithGoogle = signInWithGoogle,
            dispatcher = testDispatcher
        )

        val currentUser = viewModel.authState.value.currentUser

        assertNull(currentUser)
    }

    @Test
    fun `setCurrentUser - Test if sets a user during init when there is authenticated user`() {
        // mock the current user
        val mockedUser = mockk<User>()
        every { mockedUser.email } returns "tester@foo.com"
        every { mockedUser.id } returns "1122334455"
        every { mockedUser.name } returns "Foo Bar"
        every { getCurrentUser() } returns mockedUser

        val viewModel = AuthViewModel(
            getCurrentUser = getCurrentUser,
            signOutUser = signOutUser,
            registerUser = registerUser,
            signInWithEmailAndPassword = signInWithEmailAndPassword,
            signInWithGoogle = signInWithGoogle,
            dispatcher = testDispatcher
        )

        val currentUser = viewModel.authState.value.currentUser

        assertNotNull(currentUser)
        assertEquals("tester@foo.com", currentUser!!.email)
        assertEquals("1122334455", currentUser.id)
        assertEquals("Foo Bar", currentUser.name)
    }

    @Test
    fun `logout - Test if current user becomes null after logout`() {
        // mock the current user
        val mockedUser = mockk<User>()
        every { mockedUser.email } returns "tester@foo.com"
        every { mockedUser.id } returns "1122334455"
        every { mockedUser.name } returns "Foo Bar"
        every { getCurrentUser() } returns mockedUser
        every { signOutUser() } returns Unit

        val viewModel = AuthViewModel(
            getCurrentUser = getCurrentUser,
            signOutUser = signOutUser,
            registerUser = registerUser,
            signInWithEmailAndPassword = signInWithEmailAndPassword,
            signInWithGoogle = signInWithGoogle,
            dispatcher = testDispatcher
        )

        viewModel.logout()

        val currentUser = viewModel.authState.value.currentUser

        assertNull(currentUser)
    }

    @Test
    fun `signInWithGoogle - Test if sign in with Google yields user`() = runTest {
        val mockedUser = mockk<User>()
        val userResult = AuthRepository.UserResult(user = mockedUser)

        coEvery { signInWithGoogle() } returns userResult

        val onGoogleSignIn: (user: User?, exception: Exception?) -> Unit = {
            user, exception ->
            assertSame(mockedUser, user)
            assertNull(exception)
        }

        val viewModel = AuthViewModel(
            getCurrentUser = getCurrentUser,
            signOutUser = signOutUser,
            registerUser = registerUser,
            signInWithEmailAndPassword = signInWithEmailAndPassword,
            signInWithGoogle = signInWithGoogle,
            dispatcher = testDispatcher
        )

        viewModel.signInWithGoogle(onGoogleSignIn)
    }

    @Test
    fun `signInWithGoogle - Test if sign in with Google yields exception when there is error`() = runTest {
        val testException = Exception("Foo exception")
        val userResult = AuthRepository.UserResult(exception = testException)

        coEvery { signInWithGoogle() } returns userResult

        val onGoogleSignIn: (user: User?, exception: Exception?) -> Unit = {
                user, exception ->
            assertNull(user)
            assertSame(testException, exception)
        }

        val viewModel = AuthViewModel(
            getCurrentUser = getCurrentUser,
            signOutUser = signOutUser,
            registerUser = registerUser,
            signInWithEmailAndPassword = signInWithEmailAndPassword,
            signInWithGoogle = signInWithGoogle,
            dispatcher = testDispatcher
        )

        viewModel.signInWithGoogle(onGoogleSignIn)
    }
}
