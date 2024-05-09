package church.thegrowpoint.foundations.modules.content.data.datasources

import android.content.Context
import android.content.res.AssetManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.io.InputStream

class MockedInputStream(private var inputBytes: ByteArray) : InputStream() {
    override fun read(): Int {
        if (inputBytes.isEmpty()) {
            return -1
        }

        val firstByte = inputBytes[0]
        inputBytes = inputBytes.copyOfRange(1, inputBytes.size)
        return firstByte.toInt()
    }
}

class AssetsServiceTest {
    private val testJson = """
         {
            "name": "Ariel Magbanua",
            "gender": "M",
            "status": "Married"
         }   
    """.trimIndent()

    @Test
    fun readContentFromAsset_shouldAbleToReadContentFromAsset() {
        val mockedContext = mockk<Context>()
        val assetManager = mockk<AssetManager>()
        val mockedInputStream = MockedInputStream(testJson.toByteArray())

        every { assetManager.open("test.json") } returns mockedInputStream
        every { mockedContext.assets } returns assetManager

        val assetsService = AssetsServiceImplementation(mockedContext)
        val result = assetsService.readAsset("test.json")

        assert(result == testJson)
    }
}
