package church.thegrowpoint.foundations.auth

import android.content.Context
import church.thegrowpoint.foundations.auth.data.repositories.AuthRepositoryImplementation
import church.thegrowpoint.foundations.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.auth.domain.usecases.GetGoogleSignInClientIntent
import church.thegrowpoint.foundations.auth.domain.usecases.GoogleSignInTask
import church.thegrowpoint.foundations.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.auth.domain.usecases.SignOutUser
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object Module {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
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
    fun provideGetGoogleSignInClientIntent(@ApplicationContext context: Context): GetGoogleSignInClientIntent {
        return GetGoogleSignInClientIntent(context)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInTask(authRepository: AuthRepository): GoogleSignInTask {
        return GoogleSignInTask(authRepository)
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
}
