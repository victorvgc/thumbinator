package com.carvalho.thumbinator.feature.login.di

import android.content.Context
import com.carvalho.thumbinator.feature.login.data.data_source.LoginRemoteDataSourceImpl
import com.carvalho.thumbinator.feature.login.data.repository.LoginRepositoryImpl
import com.carvalho.thumbinator.feature.login.domain.data_source.LoginDataSource
import com.carvalho.thumbinator.feature.login.domain.repository.LoginRepository
import com.carvalho.thumbinator.feature.login.domain.use_case.DoLoginUseCase
import com.carvalho.thumbinator.feature.login.domain.use_case.RegisterUserUseCase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractLoginModule {
    @Binds
    abstract fun bindLoginRemoteDataSource(impl: LoginRemoteDataSourceImpl): LoginDataSource.Remote

    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun providesDoLoginUseCase(repository: LoginRepository): DoLoginUseCase {
        return DoLoginUseCase(repository)
    }

    @Provides
    fun providesRegisterUserUseCase(repository: LoginRepository): RegisterUserUseCase {
        return RegisterUserUseCase(repository)
    }
}