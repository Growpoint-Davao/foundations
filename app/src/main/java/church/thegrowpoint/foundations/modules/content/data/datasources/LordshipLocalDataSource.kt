package church.thegrowpoint.foundations.modules.content.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import church.thegrowpoint.foundations.modules.Routes
import javax.inject.Inject

/**
 * # LordshipLocalDataSource
 *
 * The local data source for lordship content.
 */
class LordshipLocalDataSource @Inject constructor(
    dataStore: DataStore<Preferences>
) : BaseContentLocalDataSource(section = Routes.LORDSHIP.route, dataStore = dataStore)
