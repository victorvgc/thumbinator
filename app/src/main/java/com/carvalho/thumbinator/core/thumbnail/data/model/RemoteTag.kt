package com.carvalho.thumbinator.core.thumbnail.data.model

import com.carvalho.thumbinator.core.thumbnail.domain.model.TagColor
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag

data class RemoteTag(
    var name: String = "",
    val color: String = "",
    val description: String = ""
) {
    companion object {
        fun fromAppModel(thumbTag: ThumbTag): RemoteTag {
            return RemoteTag(
                name = thumbTag.name,
                description = thumbTag.description,
                color = thumbTag.color.name
            )
        }
    }

    fun toAppModel(): ThumbTag {
        return ThumbTag(
            name = this.name,
            description = this.description,
            color = TagColor.valueOf(color.uppercase())
        )
    }
}