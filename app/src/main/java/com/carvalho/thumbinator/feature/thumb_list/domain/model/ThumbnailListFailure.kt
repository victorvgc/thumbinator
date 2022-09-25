package com.carvalho.thumbinator.feature.thumb_list.domain.model

sealed class ThumbnailListFailure {
    object EmptyList: ThumbnailListFailure()
    data class RemoteFailure(val msg: String): ThumbnailListFailure()
}