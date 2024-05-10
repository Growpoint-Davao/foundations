package church.thegrowpoint.foundations.modules.content.domain.usecases

import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetContentTest {
    private val mockedContentRepository = mockk<ContentRepository>()

    @Test
    fun invoke_shouldReturnContentIfExist() {
        val gettingStartedContent = listOf(
            listOf(
                "Foundations",
                "Bible Study App"
            ),
            listOf(
                "Bible",
                "Notebook"
            )
        )

        val salvationContent = listOf(
            listOf(
                "Jesus is Lord!",
                "Messiah",
                "Faith Alone"
            )
        )

        every { mockedContentRepository.section("gettingStarted") } returns gettingStartedContent
        every { mockedContentRepository.section("salvation") } returns salvationContent

        every { mockedContentRepository.section(any()) } answers {
            val arg = firstArg<String>()

            if (arg != "gettingStarted" && arg != "salvation") {
                null
            } else if (arg == "gettingStarted") {
                gettingStartedContent
            } else {
                salvationContent
            }
        }

        val getContent = GetContent(mockedContentRepository)

        val gettingStarted = getContent("gettingStarted")
        val salvation = getContent("salvation")

        assertNull(getContent("unknown"))
        assertEquals("Foundations", gettingStarted!![0][0])
        assertEquals("Notebook", gettingStarted[1][1])
        assertEquals("Messiah", salvation!![0][1])
    }
}
