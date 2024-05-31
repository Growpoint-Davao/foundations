package church.thegrowpoint.foundations.modules.content.data.repositories

import church.thegrowpoint.foundations.modules.content.data.datasources.BaseContentLocalDataSource
import javax.inject.Inject

class LordshipLocalRepositoryImplementation @Inject constructor(
    private val localDataSource: BaseContentLocalDataSource
) : LocalDataSourceFlowRepository(localDataSource)
