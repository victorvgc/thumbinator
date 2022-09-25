package com.carvalho.thumbinator.feature.home.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.feature.thumb_list.domain.use_case.FetchThumbnailListUseCase
import com.carvalho.thumbinator.feature.thumb_list.domain.use_case.GetThumbTagListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val thumbnailListUseCase: FetchThumbnailListUseCase,
    private val tagListUseCase: GetThumbTagListUseCase
) : ViewModel() {

    companion object {
        private const val TOTAL_SCREENS = 3

        private const val HOME_POS = 0
        private const val STATISTICS_POS = 1
        private const val PROFILE_POS = 2
    }

    private val _uiState =
        mutableStateOf<BaseState<HomeFailure, HomeState>>(
            BaseState.Loading(
                HomeState.LoadingProgress(
                    0f
                )
            )
        )
    val uiState: State<BaseState<HomeFailure, HomeState>> = _uiState

    private var dataLoadingProgress = mutableListOf<Boolean>()

    private val deleteThis = CurrentUser(
        "fake user",
        "fake@email.com",
        "photo_url",
        "user_id"
    )

    private val _thumbList = mutableStateOf<List<Thumbnail>>(emptyList())
    val thumbList: State<List<Thumbnail>> = _thumbList

    init {
        initProgressCounter()
        viewModelScope.launch {
            loadThumbList()
        }
        loadStatistics()
        loadProfile()
    }

    private fun initProgressCounter() {
        for (i in 0 until TOTAL_SCREENS) {
            dataLoadingProgress.add(false)
        }
    }

    private suspend fun loadThumbList() {
        thumbnailListUseCase(
            deleteThis
        ).collectLatest {
            for (thumb in it) {
                val tagList = tagListUseCase(deleteThis, thumb.tagList)

                thumb.tagList = tagList
            }

            _thumbList.value = it

            updateLoading(HOME_POS)
        }

    }

    private fun updateLoading(screenPos: Int, stats: Boolean = true) {
        dataLoadingProgress[screenPos] = stats
        evaluateLoading()
    }

    private fun loadStatistics() {
        updateLoading(STATISTICS_POS)
    }

    private fun loadProfile() {
        updateLoading(PROFILE_POS)
    }

    fun onThumbClick(thumbnail: Thumbnail) {

    }

    private fun evaluateLoading(): Boolean {
        var loaded = true
        var totalLoaded = 0

        dataLoadingProgress.forEach {
            loaded = it
            if (it) totalLoaded++
        }

        if (loaded) {
            _uiState.value = BaseState.Success(HomeState.LoadedState)
        } else {
            val percentLoaded = (totalLoaded.toFloat() / dataLoadingProgress.size.toFloat()) * 100f

            _uiState.value = BaseState.Loading(data = HomeState.LoadingProgress(percentLoaded))
        }

        return loaded
    }
}