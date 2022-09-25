package com.carvalho.thumbinator.feature.thumb_list.ui

import com.carvalho.thumbinator.core.thumbnail.domain.model.TagColor
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbImage
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import java.util.*
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

val stubImageList = listOf(
    ThumbImage(
        url = "https://i.ytimg.com/vi/sPYh1-czc4I/maxresdefault.jpg",
        approved = false
    ),
    ThumbImage(
        url = "https://i.ytimg.com/vi/Sp3dFF-Bts0/maxresdefault.jpg",
        approved = false
    ),
    ThumbImage(
        url = "https://i.ytimg.com/vi/xyVfLxV08I0/maxresdefault.jpg",
        approved = false
    ),
    ThumbImage(
        url = "https://i.ytimg.com/vi/pbdwbva4-WI/maxresdefault.jpg",
        approved = false
    )
)

val stubTagList = listOf(
    ThumbTag(
        name = "approved",
        color = TagColor.GREEN
    ),
    ThumbTag(
        name = "main",
        color = TagColor.BLUE
    ),
    ThumbTag(
        name = "very long tag that may use a lot os space",
        color = TagColor.RED
    ),
    ThumbTag(
        name = "approved",
        color = TagColor.GREEN
    ),
    ThumbTag(
        name = "main",
        color = TagColor.BLUE
    ),
    ThumbTag(
        name = "hard",
        color = TagColor.RED
    )
)

val stubThumbList = listOf(
    Thumbnail(
        title = "Test title 1 - May be too long and will break lines",
        observation = "",
        createdAt = Date(),
        executionDuration = 3.days,
        imageList = stubImageList.subList(0, 2),
        tagList = stubTagList.subList(0, 2)
    ),
    Thumbnail(
        title = "Test title 2 - May be too long and will break lines",
        observation = "",
        createdAt = Date(),
        executionDuration = 2.days,
        imageList = stubImageList.subList(0, 1),
        tagList = stubTagList
    ),
    Thumbnail(
        title = "Test title 3 - May be too long and will break lines",
        observation = "",
        createdAt = Date(),
        executionDuration = 1.days,
        imageList = stubImageList.subList(0, 3),
        tagList = emptyList()
    ),
    Thumbnail(
        title = "Test title 4 - May be too long and will break lines",
        observation = "",
        createdAt = Date(),
        executionDuration = 40.minutes,
        imageList = stubImageList,
        tagList = stubTagList
    ),
    Thumbnail(
        title = "Test title 5 - May be too long and will break lines",
        observation = "",
        createdAt = Date(),
        executionDuration = 15.minutes,
        imageList = emptyList(),
        tagList = stubTagList
    )
)
