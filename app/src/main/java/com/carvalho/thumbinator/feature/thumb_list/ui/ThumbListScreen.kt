package com.carvalho.thumbinator.feature.thumb_list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.feature.home.ui.StubScreen

@Composable
fun ThumbListScreen(
    modifier: Modifier = Modifier,
    thumbList: State<List<Thumbnail>>,
    onClick: (thumbnail: Thumbnail) -> Unit
) {
    val list = remember {
        thumbList
    }

    if (list.value.isEmpty()) {
        StubScreen(text = "No thumbs available")
    } else {

        LazyColumn(
            modifier = modifier.fillMaxHeight(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list.value) { thumbnail ->
                ItemThumbCard(thumb = thumbnail, onClick = onClick)
            }
        }
    }
}