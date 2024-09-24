package church.thegrowpoint.foundations.modules.content.presentation

import android.content.Context
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.content.Routes
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
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
    fun setNavigationDrawerItemSelected_shouldBeAbleToUpdateSelectedState() {
        val contentViewModel = ContentViewModel(
            context = mockedContext,
            dispatcher = testDispatcher
        )

        // assert initial value
        assertEquals(Routes.GETTING_STARTED.route, contentViewModel.navigationUIState.value.selectedSectionRoute)

        contentViewModel.setSelectedSectionRoute(Routes.SALVATION)
        assertEquals(Routes.SALVATION.route, contentViewModel.navigationUIState.value.selectedSectionRoute)
    }
}
