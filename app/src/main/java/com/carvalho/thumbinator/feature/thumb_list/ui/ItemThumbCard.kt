package com.carvalho.thumbinator.feature.thumb_list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbImage
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.core.utils.format

@Composable
fun ItemThumbCard(
    modifier: Modifier = Modifier,
    thumb: Thumbnail,
    onClick: (thumb: Thumbnail) -> Unit
) {
    ElevatedCard(
        modifier = modifier.clickable { onClick(thumb) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                when (thumb.sortedThumbList.size) {
                    0 -> {
                        // nothing
                    }
                    1 -> SingleImage(thumbImage = thumb.sortedThumbList.first())
                    2 -> TwoImages(thumbImageList = thumb.sortedThumbList)
                    else -> ThreeImagesOrMore(thumbImageList = thumb.sortedThumbList)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0f, 0f, 0f, 0.5f)),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = thumb.createdAt.format(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = thumb.title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )

            if (thumb.tagList.isNotEmpty())
                ThumbTagDisplay(thumbTagList = thumb.tagList)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun SingleImage(thumbImage: ThumbImage) {
    Image(
        modifier = Modifier.height(250.dp),
        painter = rememberImagePainter(data = thumbImage.url),
        contentScale = ContentScale.Crop,
        contentDescription = "Thumbnail image",
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun TwoImages(thumbImageList: List<ThumbImage>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.5f),
            painter = rememberImagePainter(data = thumbImageList.first().url),
            contentScale = ContentScale.Crop,
            contentDescription = "Thumbnail image",
        )
        Image(
            modifier = Modifier.fillMaxWidth(1f),
            painter = rememberImagePainter(data = thumbImageList.last().url),
            contentScale = ContentScale.Crop,
            contentDescription = "Thumbnail image",
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ThreeImagesOrMore(thumbImageList: List<ThumbImage>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            painter = rememberImagePainter(data = thumbImageList.first().url),
            contentScale = ContentScale.Crop,
            contentDescription = "Thumbnail image",
        )
        Column {
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f),
                painter = rememberImagePainter(data = thumbImageList[1].url),
                contentScale = ContentScale.Crop,
                contentDescription = "Thumbnail image",
            )
            Box(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    painter = rememberImagePainter(data = thumbImageList[2].url),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Thumbnail image",
                )
                if (thumbImageList.size > 3) {
                    MoreThumbsOverlay(size = thumbImageList.size - 3)
                }
            }
        }
    }
}

@Composable
private fun MoreThumbsOverlay(size: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0f, 0f, 0f, 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "+$size",
            style = TextStyle(
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,

                )
        )
    }
}

@Composable
private fun ThumbTagDisplay(thumbTagList: List<ThumbTag>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(thumbTagList) { tag ->
            ItemThumbTag(tag = tag)
        }
    }
}

@Composable
private fun ItemThumbTag(tag: ThumbTag) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = tag.color.background.copy(alpha = 0.3f)
        ),
        border = BorderStroke(2.dp, tag.color.background),
        shape = RoundedCornerShape(200.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(8.dp, 4.dp),
                text = tag.name.uppercase(),
                style = TextStyle(
                    color = tag.color.text,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}