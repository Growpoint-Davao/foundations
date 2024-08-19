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
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SendResetPasswordLink
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.UpdateDataStoreSkipAuthFlow
import church.thegrowpoint.foundations.modules.content.Routes
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentLocalDataSourceImplementation
import church.thegrowpoint.foundations.modules.content.data.datasources.FirestoreRemoteDataSource
import church.thegrowpoint.foundations.modules.content.data.repositories.ContentRemoteRepositoryImplementation
import church.thegrowpoint.foundations.modules.content.data.repositories.LocalDataSourceFlowRepository
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentDataSourceFlowRepository
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRemoteRepository
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentBooleanAnswerDataStoreFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentDataStoreAnswersFlow
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContentRemoteAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreAnswers
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentDataStoreBooleanAnswer
import church.thegrowpoint.foundations.modules.content.domain.usecases.SetContentRemoteAnswers
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

// creates data store for power section
val Context.powerDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.POWER.route)

// creates data store for devotion section
val Context.devotionDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.DEVOTION.route)

// creates data store for church section
val Context.churchDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.CHURCH.route)

// creates data store for discipleship section
val Context.discipleshipDataStore: DataStore<Preferences> by preferencesDataStore(name = Routes.DISCIPLESHIP.route)


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Salvation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Lordship

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Identity

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Power

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Devotion

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Church

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Discipleship

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

    @Power
    @Provides
    @Singleton
    fun providePowerContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.POWER.route,
            dataStore = context.powerDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.DEVOTION.route,
            dataStore = context.devotionDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.CHURCH.route,
            dataStore = context.churchDataStore
        )

        return LocalDataSourceFlowRepository(localDataSource = localDataSource)
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleshipContentDataSourceFlowRepository(
        @ApplicationContext context: Context
    ): ContentDataSourceFlowRepository {
        val localDataSource = ContentLocalDataSourceImplementation(
            section = Routes.DISCIPLESHIP.route,
            dataStore = context.discipleshipDataStore
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

    @Power
    @Provides
    @Singleton
    fun providePowerGetContentAnswersDataStoreFlowUseCase(
        @Power contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Power
    @Provides
    @Singleton
    fun providePowerSetContentAnswersDataStoreUseCase(
        @Power contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionGetContentAnswersDataStoreFlowUseCase(
        @Devotion contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionSetContentAnswersDataStoreUseCase(
        @Devotion contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchGetContentAnswersDataStoreFlowUseCase(
        @Church contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchSetContentAnswersDataStoreUseCase(
        @Church contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleshipGetContentAnswersDataStoreFlowUseCase(
        @Discipleship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): GetContentDataStoreAnswersFlow {
        return GetContentDataStoreAnswersFlow(contentDataSourceFlowRepository)
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleshipSetContentAnswersDataStoreUseCase(
        @Discipleship contentDataSourceFlowRepository: ContentDataSourceFlowRepository
    ): SetContentDataStoreAnswers {
        return SetContentDataStoreAnswers(contentDataSourceFlowRepository)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.SALVATION.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationSetContentRemoteAnswers(
        @Salvation remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Salvation
    @Provides
    @Singleton
    fun provideSalvationGetContentRemoteAnswers(
        @Salvation remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.LORDSHIP.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipSetContentRemoteAnswers(
        @Lordship remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Lordship
    @Provides
    @Singleton
    fun provideLordshipGetContentRemoteAnswers(
        @Lordship remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentityContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.IDENTITY.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentitySetContentRemoteAnswers(
        @Identity remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Identity
    @Provides
    @Singleton
    fun provideIdentityGetContentRemoteAnswers(
        @Identity remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Power
    @Provides
    @Singleton
    fun providePowerContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.POWER.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Power
    @Provides
    @Singleton
    fun providePowerSetContentRemoteAnswers(
        @Power remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Power
    @Provides
    @Singleton
    fun providePowerGetContentRemoteAnswers(
        @Power remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.DEVOTION.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionSetContentRemoteAnswers(
        @Devotion remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Devotion
    @Provides
    @Singleton
    fun provideDevotionGetContentRemoteAnswers(
        @Devotion remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.CHURCH.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchSetContentRemoteAnswers(
        @Church remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Church
    @Provides
    @Singleton
    fun provideChurchGetContentRemoteAnswers(
        @Church remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleContentRemoteRepository(): ContentRemoteRepository? {
        // get the current user, if no current user them return null
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return null

        // the remote data source instance for salvation section
        val dataSource = FirestoreRemoteDataSource(
            collection = Routes.DISCIPLESHIP.route,
            userID = currentUser.uid,
            db = Firebase.firestore
        )

        return ContentRemoteRepositoryImplementation(dataSource)
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleshipSetContentRemoteAnswers(
        @Discipleship remoteRepository: ContentRemoteRepository?
    ): SetContentRemoteAnswers? {
        return remoteRepository?.let { SetContentRemoteAnswers(it) }
    }

    @Discipleship
    @Provides
    @Singleton
    fun provideDiscipleGetContentRemoteAnswers(
        @Discipleship remoteRepository: ContentRemoteRepository?
    ): GetContentRemoteAnswers? {
        return remoteRepository?.let { GetContentRemoteAnswers(it) }
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
    fun provideSendResetPasswordLink(authRepository: AuthRepository): SendResetPasswordLink {
        return SendResetPasswordLink(authRepository)
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
