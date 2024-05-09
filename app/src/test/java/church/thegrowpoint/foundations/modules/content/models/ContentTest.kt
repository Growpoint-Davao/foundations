package church.thegrowpoint.foundations.modules.content.models

import church.thegrowpoint.foundations.modules.content.data.models.Content
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ContentTest {
    @Test
    fun constructor_shouldBeAbleToCreateContent() {
        val content = Content(
            gettingStarted = listOf(),
            salvation = listOf()
        )

        assertNotNull(content.gettingStarted)
        assertNotNull(content.salvation)
        assertNull(content.lordship)
        assertNull(content.identity)
        assertNull(content.power)
        assertNull(content.devotion)
        assertNull(content.church)
        assertNull(content.discipleship)
    }
}
