package church.thegrowpoint.foundations.modules.content.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import church.thegrowpoint.foundations.modules.Routes
import javax.inject.Inject

/**
 * # SalvationLocalDataSource
 *
 * The local data source for salvation content.
 */
class SalvationLocalDataSource @Inject constructor(
    dataStore: DataStore<Preferences>
) : BaseContentLocalDataSource(section = Routes.GETTING_STARTED.route, dataStore = dataStore)
