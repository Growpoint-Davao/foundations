package church.thegrowpoint.foundations.modules.auth.data.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "skip_auth")

fun Flow<Preferences>.flowData(key: Preferences.Key<Int>): Flow<Int> {
    return this.map { preferences -> preferences[key] ?: 0 }
}

/**
 * # AuthLocalDataSource
 *
 * The auth local data source interface.
 */
interface AuthLocalDataSource {
    /**
     * Retrieves the skip auth flow of data.
     *
     * @return Returns the skip flow data.
     */
    fun getSkipAuthFlow(): Flow<Int>

    /**
     * Updates skip auth.
     *
     * @param status The status of skip auth.
     */
    suspend fun updateSkipAuth(status: Int)
}

/**
 * # AuthLocalDataSourceImplementation
 *
 * @property appContext Application context instance.
 */
class AuthLocalDataSourceImplementation @Inject constructor(
    @ApplicationContext context: Context
) : AuthLocalDataSource {
    /**
     * Application context instance.
     */
    private var appContext: Context = context

    /**
     * The skip auth key.
     */
    private val skipAuthKey = intPreferencesKey("skip_auth")

    /**
     * Retrieves the skip auth flow of data.
     *
     * @return Returns the skip flow data.
     */
    override fun getSkipAuthFlow(): Flow<Int> {
        return appContext.dataStore.data.flowData(skipAuthKey)
    }

    /**
     * Updates skip auth.
     *
     * @param status The status of skip auth.
     */
    override suspend fun updateSkipAuth(status: Int) {
        val skipAuthKey = intPreferencesKey("skip_auth")

        appContext.dataStore.edit { settings -> settings[skipAuthKey] = status }
    }
}
