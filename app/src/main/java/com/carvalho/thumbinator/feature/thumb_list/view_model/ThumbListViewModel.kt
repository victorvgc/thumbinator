package com.carvalho.thumbinator.feature.thumb_list.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.feature.thumb_list.domain.model.ThumbnailListFailure
import com.carvalho.thumbinator.feature.thumb_list.domain.use_case.FetchThumbnailListUseCase
import com.carvalho.thumbinator.feature.thumb_list.domain.use_case.GetThumbTagListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThumbListViewModel @Inject constructor(
    private val fetchThumbnailListUseCase: FetchThumbnailListUseCase,
    private val getThumbTagListUseCase: GetThumbTagListUseCase,
    // todo: current user use case
) : ViewModel() {
    private val _uiState =
        mutableStateOf<BaseState<ThumbnailListFailure, List<Thumbnail>>>(BaseState.Loading())
    val uiState: State<BaseState<ThumbnailListFailure, List<Thumbnail>>> = _uiState

    private val deleteThis = CurrentUser(
        "fake user",
        "fake@email.com",
        "photo_url",
        "user_id"
    )

    init {

        viewModelScope.launch {
            fetchThumbnailListUseCase(
                deleteThis
            ).collectLatest {
                if (it.isEmpty()) {
                    _uiState.value = BaseState.Failure("empty list", ThumbnailListFailure.EmptyList)
                } else {
                    for (thumb in it) {
                        val tagList = getThumbTagListUseCase(deleteThis, thumb.tagList)

                        thumb.tagList = tagList
                    }

                    _uiState.value = BaseState.Success(it)
                }
            }
        }
    }
}