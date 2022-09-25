package com.carvalho.thumbinator.core.thumbnail.data.model

import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbImage

data class RemoteThumbImage(
    val approved: Boolean = false,
    val url: String = ""
) {
    companion object {
        fun fromAppModel(thumbImage: ThumbImage): RemoteThumbImage {
            return RemoteThumbImage(
                approved = thumbImage.approved,
                url = thumbImage.url
            )
        }
    }

    fun toAppModel(): ThumbImage {
        return ThumbImage(
            approved = this.approved,
            url = this.url
        )
    }
}