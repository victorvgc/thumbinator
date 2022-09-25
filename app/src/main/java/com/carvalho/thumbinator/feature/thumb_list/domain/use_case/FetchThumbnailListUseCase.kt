package com.carvalho.thumbinator.feature.thumb_list.domain.use_case

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.core.thumbnail.domain.repository.ThumbnailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchThumbnailListUseCase @Inject constructor(private val repository: ThumbnailRepository) {
    suspend operator fun invoke(currentUser: CurrentUser): Flow<List<Thumbnail>> =
        repository.fetchThumbnailList(currentUser)
}