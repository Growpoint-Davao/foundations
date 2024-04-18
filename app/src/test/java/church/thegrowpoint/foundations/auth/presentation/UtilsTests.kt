package church.thegrowpoint.foundations.auth.presentation

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTests {
    @Test
    fun validEmailTest() {
        val validEmail1 = "test@testing.com"
        val validEmail2 = "test.tester@testing.net"
        val validEmail3 = "test-dev@testing.io"
        val invalidEmail1 = "test"
        val invalidEmail2 = "1122@nice"
        val invalidEmail3 = " "

        assertEquals(true, validEmail1.validEmail())
        assertEquals(true, validEmail2.validEmail())
        assertEquals(true, validEmail3.validEmail())
        assertEquals(false, invalidEmail1.validEmail())
        assertEquals(false, invalidEmail2.validEmail())
        assertEquals(false, invalidEmail3.validEmail())
    }

    @Test
    fun validPasswordLengthTest() {
        val validPW = "112233445566"
        val invalidPW = "12345"

        assertEquals(true, validPW.validPasswordLength())
        assertEquals(false, invalidPW.validPasswordLength())
    }
}
