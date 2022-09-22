package com.carvalho.thumbinator.feature.login.di

import com.carvalho.thumbinator.feature.login.data.data_source.LoginRemoteDataSourceImpl
import com.carvalho.thumbinator.feature.login.data.repository.LoginRepositoryImpl
import com.carvalho.thumbinator.feature.login.domain.data_source.LoginDataSource
import com.carvalho.thumbinator.feature.login.domain.repository.LoginRepository
import com.carvalho.thumbinator.feature.login.domain.use_case.DoLoginUseCase
import com.carvalho.thumbinator.feature.login.domain.use_case.RegisterUserUseCase
import com.carvalho.thumbinator.feature.login.domain.use_case.ResetPasswordUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {
    @Binds
    abstract fun bindLoginRemoteDataSource(impl: LoginRemoteDataSourceImpl): LoginDataSource.Remote

    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}