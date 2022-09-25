package com.carvalho.thumbinator.core.thumbnail.data.model

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.TagColor
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbImage
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

data class RemoteThumbnail(
    val id: String = "",
    val created_at: Long = 0,
    val duration: Long = 0,
    val image_list: List<RemoteThumbImage> = emptyList(),
    val obs: String = "",
    val tag_list: List<String> = emptyList(),
    val title: String = ""
) {
    companion object {
        fun fromAppModel(currentUser: CurrentUser, thumbnail: Thumbnail): RemoteThumbnail {
            val imageList = mutableListOf<RemoteThumbImage>()
            thumbnail.imageList.forEach {
                imageList.add(RemoteThumbImage.fromAppModel(it))
            }

            val tagList = mutableListOf<String>()
            thumbnail.tagList.forEach {
                tagList.add(it.name)
            }

            return RemoteThumbnail(
                id = thumbnail.id,
                created_at = thumbnail.createdAt.time,
                duration = thumbnail.executionDuration.inWholeMilliseconds,
                image_list = imageList,
                obs = thumbnail.observation,
                tag_list = tagList,
                title = thumbnail.title
            )
        }
    }

    fun toAppModel(): Thumbnail {
        val tagList = mutableListOf<ThumbTag>()

        tag_list.forEach {
            tagList.add(
                ThumbTag(
                    name = it,
                    description = "",
                    color = TagColor.BLACK
                )
            )
        }

        val imageList = mutableListOf<ThumbImage>()
        this.image_list.forEach {
            imageList.add(it.toAppModel())
        }

        return Thumbnail(
            id = this.id,
            title = this.title,
            observation = this.obs,
            createdAt = Date(this.created_at),
            executionDuration = this.duration.milliseconds,
            imageList = imageList,
            tagList = tagList
        )
    }
}
