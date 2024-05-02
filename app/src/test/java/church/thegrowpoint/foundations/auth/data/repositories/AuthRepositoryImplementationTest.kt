package church.thegrowpoint.foundations.auth.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class AuthRepositoryImplementationTest {
    private val mockedFirebaseAuth = mockk<FirebaseAuth>()
    private val mockedFirebaseUser = mockk<FirebaseUser>()

    @Before
    fun setUp() {
        // this avoid blocking of coEvery
        // Similar issues:
        // https://github.com/mockk/mockk/issues/766
        // https://github.com/mockk/mockk/issues/344
        mockkStatic("kotlinx.coroutines.tasks.TasksKt")
    }

    /**
     * Test current user getter if it will return a user when there is a current user.
     */
    @Test
    fun currentUser_shouldReturnUserIfThereIsCurrentUser() {
        // set up mocked properties
        every { mockedFirebaseAuth.currentUser } returns mockedFirebaseUser

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)
        assertNotNull(authRepo.currentUser)
    }

    /**
     * Test current user getter if it will return a null user when there is no current user.
     */
    @Test
    fun currentUser_shouldReturnNullUserIfThereIsNoCurrentUser() {
        // set up mocked properties
        every { mockedFirebaseAuth.currentUser } returns null

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)
        assertNull(authRepo.currentUser)
    }

    /**
     * The signOut function of FirebaseAuth should be called when the signOut function of auth repository is called.
     */
    @Test
    fun signOut_firebaseSignOutShouldBeCalled() {
        every { mockedFirebaseAuth.signOut() } returns Unit

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)
        authRepo.signOut()

        verify { mockedFirebaseAuth.signOut() }
    }

    /**
     * Test register method if it can register email and password.
     */
    @Test
    fun register_emailPassword_shouldBeAbleToRegisterEmailAndPassword() = runTest {
        val email = "test@foo.com"
        val password = "Andr01d_bar_foo"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } returns mockedAuthResult

        every { mockedFirebaseAuth.createUserWithEmailAndPassword(email, password) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.register(email, password)
        val resultingUser = result!!.user
        val exception = result.exception
        assertEquals(resultingUser!!.email, mockedFirebaseUser.email)
        assertNull(exception)
    }

    /**
     * Test if it return null user when there is an exception.
     */
    @Test
    fun register_emailPassword_shouldReturnNullUserDuringException() = runTest {
        val email = "test@foo.com"
        val password = "Andr01d_bar_foo"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } throws Exception("Foo Exception")

        every { mockedFirebaseAuth.createUserWithEmailAndPassword(email, password) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.register(email, password)
        val resultingUser = result!!.user
        val exception = result.exception
        assertNull(resultingUser)
        assertEquals("Foo Exception", exception!!.message)
    }

    /**
     * Test sign in with email and password if it will return a user when sign in is successful with no exception.
     */
    @Test
    fun signInWithEmailAndPassword_emailPassword_shouldReturnUserAfterSignInWithNoException() = runTest {
        val email = "test@foo.com"
        val password = "Andr01d_bar_foo"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } returns mockedAuthResult

        every { mockedFirebaseAuth.signInWithEmailAndPassword(email, password) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.signInWithEmailAndPassword(email, password)
        val resultingUser = result!!.user
        val exception = result.exception
        assertEquals(resultingUser!!.email, mockedFirebaseUser.email)
        assertNull(exception)
    }

    /**
     * Test signInWithEmailAndPassword if it will return null user when there is exception.
     */
    @Test
    fun signInWithEmailAndPassword_emailPassword_shouldReturnNullUseAfterSignInWhenExceptionIsThrown() = runTest {
        val email = "test@foo.com"
        val password = "Andr01d_bar_foo"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } throws Exception("Foo Exception")

        every { mockedFirebaseAuth.signInWithEmailAndPassword(email, password) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.signInWithEmailAndPassword(email, password)
        val resultingUser = result!!.user
        val exception = result.exception
        assertNull(resultingUser)
        assertEquals("Foo Exception", exception!!.message)
    }

    /**
     * Test signInWithCredential if it will return a user after successful function call with no exception.
     */
    @Test
    fun signInWithCredential_credential_shouldReturnUserWhenNoException() = runTest {
        val email = "test@foo.com"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } returns mockedAuthResult

        val mockedCredential = mockk<AuthCredential>()

        every { mockedFirebaseAuth.signInWithCredential(mockedCredential) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.signInWithCredential(mockedCredential)
        val resultingUser = result!!.user
        val exception = result.exception
        assertEquals(resultingUser!!.email, mockedFirebaseUser.email)
        assertNull(exception)
    }

    /**
     * Test signInWithCredential if it will return a null user after function call with exception.
     */
    @Test
    fun signInWithCredential_credential_shouldReturnNullUserDuringException() = runTest {
        val email = "test@foo.com"

        // setup fake properties for mocked user
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns email
        every { mockedFirebaseUser.displayName } returns "Foo"

        // create mocked task auth result
        val mockedAuthResult = mockk<AuthResult>()
        every { mockedAuthResult.user } returns mockedFirebaseUser

        // create a mocked task
        val mockedAuthTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockedAuthTask.result } returns mockedAuthResult
        coEvery { mockedAuthTask.await() } throws Exception("Foo Exception")

        val mockedCredential = mockk<AuthCredential>()

        every { mockedFirebaseAuth.signInWithCredential(mockedCredential) } returns mockedAuthTask

        val authRepo = AuthRepositoryImplementation(mockedFirebaseAuth)

        val result = authRepo.signInWithCredential(mockedCredential)
        val resultingUser = result!!.user
        val exception = result.exception
        assertNull(resultingUser)
        assertEquals("Foo Exception", exception!!.message)
    }
}
