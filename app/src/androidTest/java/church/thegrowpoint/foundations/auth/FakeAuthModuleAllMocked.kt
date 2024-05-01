package church.thegrowpoint.foundations.auth

import android.content.Context
import church.thegrowpoint.foundations.auth.data.repositories.AuthRepositoryImplementation
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.auth.domain.usecases.SignOutUser
import com.google.firebase.auth.FirebaseAuth
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
    replaces = [AuthModule::class]
)
class FakeAuthModuleAllMocked {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        val firebaseAuth = mockk<FirebaseAuth>()
        every { firebaseAuth.currentUser } returns null
        return firebaseAuth
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImplementation(firebaseAuth)
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
