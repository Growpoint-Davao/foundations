package church.thegrowpoint.foundations.auth.presentation

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTests {
    @Test
    fun invalid_emails() {
        val invalidEmail1 = "test"
        val invalidEmail2 = "1122@nice"
        val invalidEmail3 = " "

        assertEquals(false, invalidEmail1.validEmail())
        assertEquals(false, invalidEmail2.validEmail())
        assertEquals(false, invalidEmail3.validEmail())
    }

    @Test
    fun valid_emails() {
        val validEmail1 = "test@testing.com"
        val validEmail2 = "test.tester@testing.net"
        val validEmail3 = "test-dev@testing.io"

        assertEquals(true, validEmail1.validEmail())
        assertEquals(true, validEmail2.validEmail())
        assertEquals(true, validEmail3.validEmail())
    }

    @Test
    fun invalid_password() {
        val invalidPW = "12345"
        assertEquals(false, invalidPW.validPasswordLength())
    }

    @Test
    fun valid_password() {
        val validPW = "112233445566"
        assertEquals(true, validPW.validPasswordLength())
    }
}
