package com.carvalho.thumbinator.core.current_user.di

import com.carvalho.thumbinator.core.current_user.data.data_source.RemoteCurrentUserDataSourceImpl
import com.carvalho.thumbinator.core.current_user.data.repository.CurrentUserRepositoryImpl
import com.carvalho.thumbinator.core.current_user.domain.data_source.CurrentUserDataSource
import com.carvalho.thumbinator.core.current_user.domain.repository.CurrentUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrentUserModule {

    @Binds
    abstract fun bindsCurrentUserRepository(impl: CurrentUserRepositoryImpl): CurrentUserRepository

    @Binds
    abstract fun bindsCurrentUserRemoteDataSource(impl: RemoteCurrentUserDataSourceImpl): CurrentUserDataSource.Remote
}