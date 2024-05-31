package church.thegrowpoint.foundations.modules.auth.data.datasources

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import church.thegrowpoint.foundations.modules.SkipAuthCodes
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthLocalDataSourceTest {
    private val mockedContext = mockk<Context>()

    @Before
    fun setUp() {
        mockkStatic(Flow<Preferences>::toFlowData)
    }

    @Test
    fun getSkipAuthFlow_shouldReturnInitialCode() = runTest {
        val flowPref = mockk<Flow<Preferences>>()
        val prefKey = intPreferencesKey("skip_auth")

        every { flowPref.toFlowData(prefKey) } returns flowOf(SkipAuthCodes.INITIAL.code, 1, 0)
        every { mockedContext.dataStore.data } returns flowPref

        val dataSource = AuthLocalDataSourceImplementation(mockedContext)
        val flow = dataSource.getSkipAuthFlow()
        assertEquals(SkipAuthCodes.INITIAL.code, flow.first())
    }
}
