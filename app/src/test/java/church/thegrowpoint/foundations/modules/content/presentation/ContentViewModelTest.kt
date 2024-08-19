package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ContentViewModelTest {
    private val mockedContext = mockk<Context>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        every { mockedContext.getString(R.string.getting_started) } returns "Getting Started"
        every { mockedContext.getString(R.string.salvation) } returns "Salvation"
        every { mockedContext.getString(R.string.lordship) } returns "Lordship"
        every { mockedContext.getString(R.string.identity) } returns "Identity"
        every { mockedContext.getString(R.string.power) } returns "Power"
        every { mockedContext.getString(R.string.devotion) } returns "Devotion"
        every { mockedContext.getString(R.string.church) } returns "Church"
        every { mockedContext.getString(R.string.discipleship) } returns "Discipleship"
    }

    @Test
    fun getSectionPageCount_shouldReturnCorrectPageCount() {
        val contentViewModel = ContentViewModel(
            context = mockedContext,
            dispatcher = testDispatcher
        )

        val gettingStartedPageCount = contentViewModel.getSectionPageCount("gettingStarted")
        val salvationPageCount = contentViewModel.getSectionPageCount("salvation")
        val unknownPageCount = contentViewModel.getSectionPageCount("unknown")

        assertEquals(3, gettingStartedPageCount)
        assertEquals(10, salvationPageCount)
        assertEquals(0, unknownPageCount)
    }

    @Test
    fun getNextSection_shouldReturnCorrectNextSection() {
        val contentViewModel = ContentViewModel(
            context = mockedContext,
            dispatcher = testDispatcher
        )

        val gettingStartedNextSection = contentViewModel.getNextSection("gettingStarted")
        val salvationNextSection = contentViewModel.getNextSection("salvation")
        val unknownNextSection = contentViewModel.getNextSection("unknown")

        assertEquals("salvation", gettingStartedNextSection)
        assertEquals("lordship", salvationNextSection)
        assertEquals(null, unknownNextSection)
    }

    @Test
    fun setNavigationDrawerItemSelected_shouldBeAbleToUpdateSelectedState() {
        val contentViewModel = ContentViewModel(
            context = mockedContext,
            dispatcher = testDispatcher
        )

        assertFalse(contentViewModel.navigationUIState.value.gettingStartedSelected)
        assertFalse(contentViewModel.navigationUIState.value.salvationSelected)

        contentViewModel.setNavigationDrawerItemSelected(
            salvationSelected = true
        )

        assertFalse(contentViewModel.navigationUIState.value.gettingStartedSelected)
        assertTrue(contentViewModel.navigationUIState.value.salvationSelected)

        contentViewModel.setNavigationDrawerItemSelected(
            gettingStartedSelected = true
        )

        assertTrue(contentViewModel.navigationUIState.value.gettingStartedSelected)
        assertFalse(contentViewModel.navigationUIState.value.salvationSelected)
    }
}
