package com.carvalho.thumbinator.core.thumbnail.data.repository

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.TagDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.thumbnail.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(private val remote: TagDataSource.Remote): TagRepository {
    override suspend fun saveTag(currentUser: CurrentUser, thumbTag: ThumbTag) {
        remote.saveTag(currentUser, thumbTag)
    }

    override suspend fun getTag(currentUser: CurrentUser, thumbTag: ThumbTag): ThumbTag? {
        return remote.getTag(currentUser, thumbTag)
    }
}