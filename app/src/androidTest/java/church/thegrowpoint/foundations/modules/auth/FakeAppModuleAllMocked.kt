package church.thegrowpoint.foundations.modules.auth

import android.content.Context
import church.thegrowpoint.foundations.modules.AppModule
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthFirestoreDataSource
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthFirestoreDataSourceImplementation
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSource
import church.thegrowpoint.foundations.modules.auth.data.datasources.AuthLocalDataSourceImplementation
import church.thegrowpoint.foundations.modules.auth.data.repositories.AuthRepositoryImplementation
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class FakeAppModuleAllMocked {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        val firebaseAuth = mockk<FirebaseAuth>()
        every { firebaseAuth.currentUser } returns null
        return firebaseAuth
    }

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(
        @ApplicationContext context: Context
    ): AuthLocalDataSource {
        return AuthLocalDataSourceImplementation(context)
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
        return SignInWithGoogle(authRepository = authRepository, context = context)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
