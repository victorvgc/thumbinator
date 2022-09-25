package com.carvalho.thumbinator.core.thumbnail.data.repository

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.ThumbnailDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.core.thumbnail.domain.repository.ThumbnailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThumbnailRepositoryImpl @Inject constructor(private val remote: ThumbnailDataSource.Remote) :
    ThumbnailRepository {
    override suspend fun fetchThumbnailList(currentUser: CurrentUser): Flow<List<Thumbnail>> {
        return remote.fetchThumbnailList(currentUser)
    }
}