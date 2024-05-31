package church.thegrowpoint.foundations.modules.content.data.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import church.thegrowpoint.foundations.modules.Routes
import church.thegrowpoint.foundations.utils.extensions.toJsonString
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * # LordshipLocalDataSource
 *
 * The local data source for lordship content.
 */
class LordshipLocalDataSource @Inject constructor(
    dataStore: DataStore<Preferences>
) : BaseContentLocalDataSource(section = Routes.LORDSHIP.route, dataStore = dataStore)
