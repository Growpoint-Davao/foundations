package church.thegrowpoint.foundations.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class StringsTests {
    @Test
    fun validEmail_returnsFalseIfInvalidEmail() {
        val invalidEmail1 = "test"
        val invalidEmail2 = "1122@nice"
        val invalidEmail3 = " "

        assertEquals(false, invalidEmail1.validEmail())
        assertEquals(false, invalidEmail2.validEmail())
        assertEquals(false, invalidEmail3.validEmail())
    }

    @Test
    fun validEmail_returnsTrueIfValidEmail() {
        val validEmail1 = "test@testing.com"
        val validEmail2 = "test.tester@testing.net"
        val validEmail3 = "test-dev@testing.io"

        assertEquals(true, validEmail1.validEmail())
        assertEquals(true, validEmail2.validEmail())
        assertEquals(true, validEmail3.validEmail())
    }

    @Test
    fun validPasswordLength_returnsFalseIfPasswordTooShort() {
        val invalidPW = "12345"
        assertEquals(false, invalidPW.validPasswordLength())
    }

    @Test
    fun validPasswordLength_returnsTrueIfPasswordIsLongEnough() {
        val validPW = "112233445566"
        assertEquals(true, validPW.validPasswordLength())
    }
}
