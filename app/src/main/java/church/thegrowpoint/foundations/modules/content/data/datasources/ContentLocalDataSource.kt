package church.thegrowpoint.foundations.modules.content.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import church.thegrowpoint.foundations.utils.extensions.toJsonString
import church.thegrowpoint.foundations.utils.extensions.toStringHashMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Extension function to transform preference json string to hashmap flow
 */
fun Flow<Preferences>.toHashMapFlow(key: Preferences.Key<String>): Flow<HashMap<String, String>> {
    return this.map { preferences -> preferences[key]?.toStringHashMap() ?: HashMap() }
}

/**
 * Extension function to transform preference to boolean flow
 */
fun Flow<Preferences>.toBooleanFlow(key: Preferences.Key<Boolean>): Flow<Boolean> {
    return this.map { preferences -> preferences[key] == true }
}

/**
 * # ContentLocalDataSource
 *
 * Content local data source interface.
 */
interface ContentLocalDataSource {
    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    fun getBooleanAnswerFlow(key: String): Flow<Boolean>

    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    suspend fun setBooleanAnswer(key: String, status: Boolean)

    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    fun getAnswersFlow(): Flow<HashMap<String, String>>

    /**
     * Set answers (which is key value pair of answers)
     */
    suspend fun setAnswers(answers: Map<String, String>)
}

/**
 * # ContentLocalDataSourceImplementation
 *
 * The class for reading and writing content locally.
 *
 * @property section the name of content section.
 */
class ContentLocalDataSourceImplementation(
    private val section: String,
    private val dataStore: DataStore<Preferences>
) : ContentLocalDataSource {
    /**
     * Preference key instance.
     */
    private val answersPreferenceKey = stringPreferencesKey("${section}_answers")

    /**
     * Get boolean answer flow.
     *
     * @param key the name or key of the checkbox question.
     * @return returns the boolean flow of checkbox answer.
     */
    override fun getBooleanAnswerFlow(key: String): Flow<Boolean> {
        val prefKey = booleanPreferencesKey("${section}_${key}")
        return dataStore.data.toBooleanFlow(prefKey)
    }

    /**
     * Set boolean answer.
     *
     * @param key the name or key of the checkbox question.
     * @param status the boolean answer.
     */
    override suspend fun setBooleanAnswer(key: String, status: Boolean) {
        val prefKey = booleanPreferencesKey("${section}_${key}")
        dataStore.edit { preference ->
            preference[prefKey] = status
        }
    }

    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    override fun getAnswersFlow(): Flow<HashMap<String, String>> {
        return dataStore.data.toHashMapFlow(answersPreferenceKey)
    }

    /**
     * Set answers (which is key value pair of answers)
     */
    override suspend fun setAnswers(answers: Map<String, String>) {
        dataStore.edit { preference ->
            preference[answersPreferenceKey] = answers.toJsonString()
        }
    }
}
