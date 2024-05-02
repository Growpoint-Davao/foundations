package church.thegrowpoint.foundations.auth.data.models

import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserTest {
    private val mockedFirebaseUser = mockk<FirebaseUser>()

    /**
     * Test if can create a new user from a firebase user instance.
     */
    @Test
    fun constructor_firebaseUser_shouldBeAbleToCreateNewUser() {
        every { mockedFirebaseUser.uid } returns "11223344"
        every { mockedFirebaseUser.email } returns "foo@testing.com"
        every { mockedFirebaseUser.displayName } returns "Foo"

        val user = User(mockedFirebaseUser)

        assertEquals("11223344", user.id)
        assertEquals("foo@testing.com", user.email)
        assertEquals("Foo", user.name)
    }
}
