package com.carvalho.thumbinator.core.thumbnail.domain.data_source

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import kotlinx.coroutines.flow.Flow

interface ThumbnailDataSource {
    interface Remote {
        suspend fun fetchThumbnailList(currentUser: CurrentUser): Flow<List<Thumbnail>>
    }
}