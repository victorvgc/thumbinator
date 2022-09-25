package com.carvalho.thumbinator.core.thumbnail.di

import com.carvalho.thumbinator.core.thumbnail.data.data_source.RemoteTagDataSourceImpl
import com.carvalho.thumbinator.core.thumbnail.data.data_source.RemoteThumbnailDataSourceImpl
import com.carvalho.thumbinator.core.thumbnail.data.repository.TagRepositoryImpl
import com.carvalho.thumbinator.core.thumbnail.data.repository.ThumbnailRepositoryImpl
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.TagDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.ThumbnailDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.repository.TagRepository
import com.carvalho.thumbinator.core.thumbnail.domain.repository.ThumbnailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ThumbnailModule {
    @Binds
    abstract fun bindTagRepository(impl: TagRepositoryImpl): TagRepository

    @Binds
    abstract fun bindThumbnailRepository(impl: ThumbnailRepositoryImpl): ThumbnailRepository

    @Binds
    abstract fun bindRemoteTagDataSource(impl: RemoteTagDataSourceImpl): TagDataSource.Remote

    @Binds
    abstract fun bindRemoteThumbnailDataSource(impl: RemoteThumbnailDataSourceImpl): ThumbnailDataSource.Remote
}