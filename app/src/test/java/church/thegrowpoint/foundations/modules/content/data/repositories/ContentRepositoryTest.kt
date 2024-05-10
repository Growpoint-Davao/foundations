package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.ContentService
import church.thegrowpoint.foundations.modules.content.data.models.Content
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ContentRepositoryTest {
    private val mockedContentService = mockk<ContentService>()

    @Test
    fun section_contentJson_shouldBeAbleToRetrieveSectionContent() {
        val testContent = Content(
            gettingStarted = listOf(
                listOf(
                    "Foundations",
                    "Bible Study App"
                ),
                listOf(
                    "Bible",
                    "Notebook"
                )
            ),
            salvation = listOf(
                listOf(
                    "Jesus is Lord!",
                    "Messiah",
                    "Faith Alone"
                )
            )
        )

        every { mockedContentService.content } returns testContent

        val contentRepository = ContentRepositoryImplementation(mockedContentService)
        val myTest = contentRepository.section("my_test")
        val power = contentRepository.section("power")
        val gettingStarted = contentRepository.section("gettingStarted")
        val salvation = contentRepository.section("salvation")

        assertNull(myTest)
        assertNull(power)
        assertEquals(2, gettingStarted!!.size)
        assertEquals("Bible Study App", gettingStarted[0][1])
        assertEquals("Bible", gettingStarted[1][0])
        assertEquals(1, salvation!!.size)
        assertEquals("Jesus is Lord!", salvation[0][0])
        assertEquals("Faith Alone", salvation[0][2])
    }
}
