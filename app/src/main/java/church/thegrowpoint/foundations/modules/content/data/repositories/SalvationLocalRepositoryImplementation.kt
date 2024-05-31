package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.BaseContentLocalDataSource
import javax.inject.Inject

/**
 * # SalvationLocalRepositoryImplementation
 *
 * The implementation for local salvation data source interface.
 */
class SalvationLocalRepositoryImplementation @Inject constructor(
    localDataSource: BaseContentLocalDataSource
) : LocalDataSourceFlowRepository(localDataSource)
