package com.carvalho.thumbinator.feature.thumb_list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail

@Composable
fun ThumbListScreen(thumbList: List<Thumbnail>, onClick: (thumbnail: Thumbnail) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(thumbList) { thumbnail ->
            ItemThumbCard(thumb = thumbnail, onClick = onClick)
        }
    }
}