package church.thegrowpoint.foundations.auth.data.models

import com.google.firebase.auth.FirebaseUser
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UserTest {
    private val mockedFirebaseUser = Mockito.mock<FirebaseUser>()

    @Before
    fun setUp() {
        whenever(mockedFirebaseUser.uid).doReturn("11223344")
        whenever(mockedFirebaseUser.email).doReturn("foo@testing.com")
        whenever(mockedFirebaseUser.displayName).doReturn("Foo")
    }

    @Test
    fun user_Constructor_CanCreateNewUser() {
        val user = User(mockedFirebaseUser)

        assertEquals("11223344", user.id)
        assertEquals("foo@testing.com", user.email)
        assertEquals("Foo", user.name)
    }
}
