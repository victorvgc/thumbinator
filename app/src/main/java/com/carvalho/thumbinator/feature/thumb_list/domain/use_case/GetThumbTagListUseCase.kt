package com.carvalho.thumbinator.feature.thumb_list.domain.use_case

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.thumbnail.domain.repository.TagRepository
import javax.inject.Inject

class GetThumbTagListUseCase @Inject constructor(private val repository: TagRepository) {
    suspend operator fun invoke(currentUser: CurrentUser, tagList: List<ThumbTag>): List<ThumbTag> {
        val returnTagList = mutableListOf<ThumbTag>()

        for (tag in tagList) {
            val result = repository.getTag(currentUser, tag)
            if (result != null)
                returnTagList.add(result)
        }

        return returnTagList
    }
}