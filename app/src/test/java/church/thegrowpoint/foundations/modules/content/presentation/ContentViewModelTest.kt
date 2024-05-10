package church.thegrowpoint.foundations.modules.content.presentation

import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContent
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentViewModelTest {
    private val mockedGetContent = mockk<GetContent>()
    private val gettingStartedContent = listOf(
        listOf(
            "Foundations",
            "Bible Study App"
        ),
        listOf(
            "Bible",
            "Notebook"
        )
    )

    private val salvationContent = listOf(
        listOf(
            "Jesus is Lord!",
            "Messiah",
            "Faith Alone"
        )
    )

    init {
        every { mockedGetContent("gettingStarted") } returns gettingStartedContent
        every { mockedGetContent("salvation") } returns salvationContent
        every { mockedGetContent(any()) } answers {
            val arg = firstArg<String>()

            if (arg != "gettingStarted" && arg != "salvation") {
                null
            } else if (arg == "gettingStarted") {
                gettingStartedContent
            } else {
                salvationContent
            }
        }
    }

    @Test
    fun getGettingStartedPageContents_shouldReturnAListOfStringsIfIndexExist() {
        val contentViewModel = ContentViewModel(mockedGetContent)
        val pageContent = contentViewModel.getGettingStartedPageContents(0)

        assertEquals("Foundations", pageContent[0])
        assertEquals(2, pageContent.size)
    }

    @Test
    fun getGettingStartedPageContents_shouldReturnEmptyListIfIndexDoesNotExist() {
        val contentViewModel = ContentViewModel(mockedGetContent)
        val emptyPageContent = contentViewModel.getGettingStartedPageContents(10)

        assertTrue(emptyPageContent.isEmpty())
    }

    @Test
    fun getSalvationPageContents_shouldReturnAListOfStringsIfIndexExist() {
        val contentViewModel = ContentViewModel(mockedGetContent)
        val pageContent = contentViewModel.getSalvationPageContents(0)

        assertEquals("Jesus is Lord!", pageContent[0])
        assertEquals(3, pageContent.size)
    }

    @Test
    fun getSalvationPageContents_shouldReturnEmptyListIfIndexDoesNotExist(){
        val contentViewModel = ContentViewModel(mockedGetContent)
        val emptyPageContent = contentViewModel.getSalvationPageContents(10)

        assertTrue(emptyPageContent.isEmpty())
    }
}
