package church.thegrowpoint.foundations.modules

import android.content.Context
import church.thegrowpoint.foundations.modules.auth.data.repositories.AuthRepositoryImplementation
import church.thegrowpoint.foundations.modules.auth.domain.repositories.AuthRepository
import church.thegrowpoint.foundations.modules.auth.domain.usecases.GetCurrentUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.RegisterUser
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithEmailAndPassword
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignInWithGoogle
import church.thegrowpoint.foundations.modules.auth.domain.usecases.SignOutUser
import church.thegrowpoint.foundations.modules.content.data.datasources.AssetsService
import church.thegrowpoint.foundations.modules.content.data.datasources.AssetsServiceImplementation
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentService
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentServiceImplementation
import church.thegrowpoint.foundations.modules.content.data.repositories.ContentRepositoryImplementation
import church.thegrowpoint.foundations.modules.content.domain.repositories.ContentRepository
import church.thegrowpoint.foundations.modules.content.domain.usecases.GetContent
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
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
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @Singleton
    fun provideAssetsService(@ApplicationContext context: Context): AssetsService {
        return AssetsServiceImplementation(context)
    }

    @Provides
    @Singleton
    fun provideContentService(assetService: AssetsService): ContentService {
        return ContentServiceImplementation(assetService)
    }

    @Provides
    @Singleton
    fun provideContentRepository(contentService: ContentService): ContentRepository {
        return ContentRepositoryImplementation(contentService)
    }

    @Provides
    @Singleton
    fun provideGetContent(contentRepository: ContentRepository): GetContent {
        return GetContent(contentRepository)
    }
}