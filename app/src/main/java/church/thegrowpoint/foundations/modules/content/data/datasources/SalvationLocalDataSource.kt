package church.thegrowpoint.foundations.modules.content.data.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.utils.extensions.toJsonString
import church.thegrowpoint.foundations.utils.extensions.toStringHashMap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// creates data store for salvation
val Context.salvationDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.SALVATION.route)

/**
 * Extension function to transform preference json string to hashmap
 */
fun Flow<Preferences>.toFlowData(key: Preferences.Key<String>): Flow<HashMap<String, String>> {
    return this.map { preferences -> preferences[key]?.toStringHashMap() ?: HashMap() }
}

/**
 * # SalvationLocalDataSource
 *
 * The local data source for salvation content.
 */
class SalvationLocalDataSource @Inject constructor(
    @ApplicationContext context: Context
) : BaseContentLocalDataSource(context = context, section = Routes.GETTING_STARTED.route) {
    /**
     * Get answers flow.
     *
     * @return returns the flow data which is key value pair of answers.
     */
    override fun getAnswersFlow(): Flow<HashMap<String, String>> {
        return appContext.salvationDataStore.data.toFlowData(preferenceKey)
    }

    /**
     * Set answers (which is key value pair of answers)
     */
    override suspend fun setAnswers(answers: HashMap<String, String>) {
        appContext.salvationDataStore.edit { preference ->
            preference[preferenceKey] = answers.toJsonString()
        }
    }
}
