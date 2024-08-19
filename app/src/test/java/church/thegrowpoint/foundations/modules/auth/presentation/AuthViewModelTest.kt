package church.thegrowpoint.foundations.modules.auth.presentation

import android.content.Context
import church.thegrowpoint.foundations.modules.content.SkipAuthCodes
import church.thegrowpoint.foundations.modules.auth.domain.models.User
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetDataStoreSkipAuthFlow
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SendResetPasswordLink
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.UpdateDataStoreSkipAuthFlow
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AuthViewModelTest {
    // Mock dependencies
    private val context = mockk<Context>()
    private lateinit var getCurrentUser: GetCurrentUser
    private lateinit var signOutUser: SignOutUser
    private lateinit var registerUser: RegisterUser
    private lateinit var signInWithEmailAndPassword: SignInWithEmailAndPassword
    private lateinit var signInWithGoogle: SignInWithGoogle
    private lateinit var getSkipAuthFlow: GetDataStoreSkipAuthFlow
    private lateinit var updateSkipAuthFlow: UpdateDataStoreSkipAuthFlow
    private lateinit var sendResetPasswordLink: SendResetPasswordLink
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
        getSkipAuthFlow = mockk<GetDataStoreSkipAuthFlow>()
        updateSkipAuthFlow = mockk<UpdateDataStoreSkipAuthFlow>()
        sendResetPasswordLink = mockk<SendResetPasswordLink>()

        every { getCurrentUser() } returns null
    }

    /**
     * Tests setCurrentUser if it can set null user whe there is no authenticated user
     */
    @Test
    fun setCurrentUser_NullUser_shouldBeAbleToSetNullUser() {
        // mock the current user
        every { getCurrentUser() } returns null

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        val currentUser = viewModel.authState.value.currentUser

        assertNull(currentUser)
    }

    /**
     * Tests setCurrentUser if it can set a user whe there is authenticated user
     */
    @Test
    fun setCurrentUser_user_shouldBeAbleToSetNonNullUser() {
        // mock the current user
        val mockedUser = mockk<User>()
        every { mockedUser.email } returns "tester@foo.com"
        every { mockedUser.id } returns "1122334455"
        every { mockedUser.name } returns "Foo Bar"
        every { getCurrentUser() } returns mockedUser

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        val currentUser = viewModel.authState.value.currentUser

        assertNotNull(currentUser)
        assertEquals("tester@foo.com", currentUser!!.email)
        assertEquals("1122334455", currentUser.id)
        assertEquals("Foo Bar", currentUser.name)
    }

    /**
     * Test if skip auth state will be set to true when skipAuthentication is called
     */
    @Test
    fun skipAuthentication_shouldSetSkipAuthToTrueWhenCalled() {
        // mock the current user
        every { getCurrentUser() } returns null

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        assertFalse(viewModel.authState.value.skipAuth)
        viewModel.skipAuthentication()
        assertTrue(viewModel.authState.value.skipAuth)
    }

    @Test
    fun logout_ifCurrentUserBecomesNullAfterLogout() = runTest {
        // mock the current user
        val mockedUser = mockk<User>()
        every { mockedUser.email } returns "tester@foo.com"
        every { mockedUser.id } returns "1122334455"
        every { mockedUser.name } returns "Foo Bar"
        every { getCurrentUser() } returns mockedUser
        every { signOutUser() } returns Unit

        coEvery { updateSkipAuthFlow(SkipAuthCodes.NOT_SKIPPED.code) } returns Unit

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        viewModel.logout()

        val currentUser = viewModel.authState.value.currentUser

        assertNull(currentUser)
    }

    @Test
    fun signInWithGoogle_ifSignInWithGoogleYieldsUser() = runTest {
        val mockedUser = mockk<User>()
        val userResult = AuthRepository.UserResult(user = mockedUser)

        coEvery { signInWithGoogle() } returns userResult

        val onGoogleSignIn: (user: User?, exception: Exception?) -> Unit = {
            user, exception ->
            assertSame(mockedUser, user)
            assertNull(exception)
        }

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        viewModel.signInWithGoogle(onGoogleSignIn = onGoogleSignIn)
    }

    @Test
    fun signInWithGoogle_ifSignInWithGoogleYieldsExceptionWhenThereIsError() = runTest {
        val testException = Exception("Foo exception")
        val userResult = AuthRepository.UserResult(exception = testException)

        coEvery { signInWithGoogle() } returns userResult

        val onGoogleSignIn: (user: User?, exception: Exception?) -> Unit = {
                user, exception ->
            assertNull(user)
            assertSame(testException, exception)
        }

        val viewModel = AuthViewModel(
            context = context,
            getCurrentUser = getCurrentUser,
            signOutUserUseCase = signOutUser,
            registerUserUseCase = registerUser,
            signInWithEmailAndPasswordUseCase = signInWithEmailAndPassword,
            signInWithGoogleUseCase = signInWithGoogle,
            dispatcher = testDispatcher,
            getSkipAuthFlowUseCase = getSkipAuthFlow,
            updateSkipAuthFlowUseCase = updateSkipAuthFlow,
            sendResetPasswordLinkUseCase = sendResetPasswordLink
        )

        viewModel.signInWithGoogle(onGoogleSignIn = onGoogleSignIn)
    }
}
