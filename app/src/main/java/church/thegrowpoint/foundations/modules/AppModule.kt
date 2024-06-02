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
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentLocalDataSourceImplementation
import church.thegrowpoint.foundations.modules.content.data.repositories.LocalDataSourceFlowRepository
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentBooleanAnswerDataStoreFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreBooleanAnswer
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

// creates data store for salvation section
val Context.salvationDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.SALVATION.route)

// creates data store for lordship section
val Context.lordshipDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.LORDSHIP.route)

// creates data store for identity section
val Context.identityDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.IDENTITY.route)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Salvation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Lordship

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Identity

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    @Salvation
    @Provides
    @Singleton
    fun provideSalvationContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        // create the local data source
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.SALVATION.route,
            dataStore = context.salvationDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        // create the local data source
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.LORDSHIP.route,
            dataStore = context.lordshipDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentityContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.IDENTITY.route,
            dataStore = context.identityDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationGetContentAnswersDataStoreFlowUseCase(
        @Salvation contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationSetContentAnswersDataStoreUseCase(
        @Salvation contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipGetContentAnswersDataStoreFlowUseCase(
        @Lordship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipSetContentAnswersDataStoreUseCase(
        @Lordship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentityGetContentAnswersDataStoreFlowUseCase(
        @Identity contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentitySetContentAnswersDataStoreUseCase(
        @Identity contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentityGetContentBooleanAnswerDataStoreFlowUseCase(
        @Identity contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentBooleanAnswerDataStoreFlow {
        return GetContentBooleanAnswerDataStoreFlow(contentDataSourceFlowRepository)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentitySetContentDataStoreBooleanAnswerUseCase(
        @Identity contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreBooleanAnswer {
        return SetContentDataStoreBooleanAnswer(contentDataSourceFlowRepository)
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
