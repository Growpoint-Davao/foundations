package church.thegrowpoint.foundations.modules.content.data.datasources

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * # AssetsService
 *
 * The interface that provides access to the assets.
 */
interface AssetsService {
    /**
     * Reads the content of an asset with the given [filename].
     */
    fun readAsset(filename: String): String?
}

/**
 * # AssetsServiceImplementation
 *
 * The implementation class for [AssetsService].
 *
 * @property context The context used to access the assets.
 */
class AssetsServiceImplementation @Inject constructor(
    private val context: Context
) : AssetsService {
    /**
     * Reads the content of an asset with the given [filename].
     */
    override fun readAsset(filename: String): String? {
        try {
            context.assets.open(filename).use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))
                return reader.readText()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
