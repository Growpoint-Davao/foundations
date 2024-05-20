package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ContentViewModelTest {
    private val mockedContext = mockk<Context>()

    @Test
    fun getSectionPageCount_shouldReturnCorrectPageCount() {
        val contentViewModel = ContentViewModel(context = mockedContext)

        val gettingStartedPageCount = contentViewModel.getSectionPageCount("gettingStarted")
        val salvationPageCount = contentViewModel.getSectionPageCount("salvation")
        val unknownPageCount = contentViewModel.getSectionPageCount("unknown")

        assertEquals(3, gettingStartedPageCount)
        assertEquals(10, salvationPageCount)
        assertEquals(0, unknownPageCount)
    }

    @Test
    fun getNextSection_shouldReturnCorrectNextSection() {
        val contentViewModel = ContentViewModel(context = mockedContext)

        val gettingStartedNextSection = contentViewModel.getNextSection("gettingStarted")
        val salvationNextSection = contentViewModel.getNextSection("salvation")
        val unknownNextSection = contentViewModel.getNextSection("unknown")

        assertEquals("salvation", gettingStartedNextSection)
        assertEquals("lordship", salvationNextSection)
        assertEquals(null, unknownNextSection)
    }
}
