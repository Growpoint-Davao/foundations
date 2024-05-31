package church.thegrowpoint.foundations.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthFirestoreDataSource
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthFirestoreDataSourceImplementation
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSource
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSourceImplementation
import church.thegrowpoint.foundations.modules.auth.data.repositories.AuthRepositoryImplementation
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetDataStoreSkipAuthFlow
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.UpdateDataStoreSkipAuthFlow
import church.thegrowpoint.foundations.modules.content.data.datasources.BaseContentLocalDataSource
import church.thegrowpoint.foundations.modules.content.data.datasources.LordshipLocalDataSource
import church.thegrowpoint.foundations.modules.content.data.datasources.SalvationLocalDataSource
import church.thegrowpoint.foundations.modules.content.data.repositories.LordshipLocalRepositoryImplementation
import church.thegrowpoint.foundations.modules.content.data.repositories.SalvationLocalRepositoryImplementation
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetDataStoreLordshipAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetDataStoreSalvationAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetDataStoreLordShipAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetDataStoreSalvationAnswers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

// creates data store for salvation
val Context.salvationDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.SALVATION.route)

// creates data store for salvation
val Context.lordshipDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.LORDSHIP.route)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Salvation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Lordship

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    // data stores here
    @Salvation
    @Provides
    @Singleton
    fun provideSalvationDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.salvationDataStore
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.lordshipDataStore
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipLocalDataSource(
        @Lordship dataStore: DataStore<Preferences>
    ): BaseContentLocalDataSource {
        return LordshipLocalDataSource(dataStore)
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipLocalRepositoryImplementation(
        @Lordship localDataSource: BaseContentLocalDataSource
    ): ContentDataSourceFlowRepository {
        return LordshipLocalRepositoryImplementation(localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetDataStoreLordshipAnswersFlow(
        @Lordship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetDataStoreLordshipAnswersFlow {
        return GetDataStoreLordshipAnswersFlow(contentDataSourceFlowRepository)
    }

    @Provides
    @Singleton
    fun provideSetDataStoreLordShipAnswers(
        @Lordship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetDataStoreLordShipAnswers {
        return SetDataStoreLordShipAnswers(contentDataSourceFlowRepository)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationLocalDataSource(
        @Salvation dataStore: DataStore<Preferences>
    ): BaseContentLocalDataSource {
        return SalvationLocalDataSource(dataStore)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationLocalRepositoryImplementation(
        @Salvation localDataSource: BaseContentLocalDataSource
    ): ContentDataSourceFlowRepository {
        return SalvationLocalRepositoryImplementation(localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetDataStoreSalvationAnswersFlow(
        @Salvation contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetDataStoreSalvationAnswersFlow {
        return GetDataStoreSalvationAnswersFlow(contentDataSourceFlowRepository)
    }

    @Provides
    @Singleton
    fun provideSetDataStoreSalvationAnswers(
        @Salvation contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetDataStoreSalvationAnswers {
        return SetDataStoreSalvationAnswers(contentDataSourceFlowRepository)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideAuthFirestoreDataSource(db: FirebaseFirestore): AuthFirestoreDataSource {
        return AuthFirestoreDataSourceImplementation(db)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        authLocalDataSource: AuthLocalDataSource,
        authFirestoreDataSource: AuthFirestoreDataSource
    ): AuthRepository {
        return AuthRepositoryImplementation(
            firebaseAuth = firebaseAuth,
            authLocalDataSource = authLocalDataSource,
            authFirestoreDataSource = authFirestoreDataSource
        )
    }

    @Provides
    @Singleton
    fun provideGetCurrentUser(authRepository: AuthRepository): GetCurrentUser {
        return GetCurrentUser(authRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUser(authRepository: AuthRepository): RegisterUser {
        return RegisterUser(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignInWithEmailAndPassword(authRepository: AuthRepository): SignInWithEmailAndPassword {
        return SignInWithEmailAndPassword(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignOutUser(authRepository: AuthRepository): SignOutUser {
        return SignOutUser(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignInWithCredential(
        authRepository: AuthRepository,
        @ApplicationContext context: Context
    ): SignInWithGoogle {
        return SignInWithGoogle(authRepository, context)
    }

    @Provides
    @Singleton
    fun provideGetSkipAuthFlow(authRepository: AuthRepository): GetDataStoreSkipAuthFlow {
        return GetDataStoreSkipAuthFlow(authRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateSkipAuthFlow(authRepository: AuthRepository): UpdateDataStoreSkipAuthFlow {
        return UpdateDataStoreSkipAuthFlow(authRepository)
    }

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(
        @ApplicationContext context: Context
    ): AuthLocalDataSource {
        return AuthLocalDataSourceImplementation(context)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
