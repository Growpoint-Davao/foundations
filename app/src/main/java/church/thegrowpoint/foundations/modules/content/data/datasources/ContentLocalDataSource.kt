package church.thegrowpoint.foundations.modules.content.data.datasources

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

/**
 * # ContentLocalDataSource
 *
 * Content local data source interface.
 */
interface ContentLocalDataSource {
    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    fun getAnswersFlow(): Flow<HashMap<String, String>>

    /**
     * Set answers (which is key value pair of answers)
     */
    suspend fun setAnswers(answers: HashMap<String, String>)
}

/**
 * # BaseContentLocalDataSource
 *
 * The base class for reading and writing content locally.
 *
 * @property section the name of content section.
 * @property appContext the application context.
 */
abstract class BaseContentLocalDataSource(
    protected val section: String,
    context: Context
) : ContentLocalDataSource {
    /**
     * Application context instance.
     */
    protected var appContext: Context = context

    /**
     * Preference key instance.
     */
    val preferenceKey = stringPreferencesKey(section)
}
