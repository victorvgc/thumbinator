package com.carvalho.thumbinator.core.thumbnail.domain.data_source

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag

interface TagDataSource {
    interface Remote {
        suspend fun saveTag(currentUser: CurrentUser, thumbTag: ThumbTag)

        suspend fun getTag(currentUser: CurrentUser, thumbTag: ThumbTag): ThumbTag?
    }
}