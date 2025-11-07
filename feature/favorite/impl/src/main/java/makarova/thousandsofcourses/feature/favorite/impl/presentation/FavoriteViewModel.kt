package makarova.thousandsofcourses.feature.favorite.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.thousandsofcourses.feature.main.api.usecase.ToggleFavoriteUseCase
import makarova.thousandsofcourses.feature.favorite.api.usecase.GetFavoriteListUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteListUseCase: GetFavoriteListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteState())
    val uiState: StateFlow<FavoriteState> = _uiState

    private val _effects = MutableSharedFlow<FavoriteEffect>()
    val effects: SharedFlow<FavoriteEffect> = _effects

    init {
        getFavoriteList()
    }

    fun reduce(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnCourseLiked -> onCourseUnLiked(event.course)
        }
    }

    private fun getFavoriteList() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val list = favoriteListUseCase.invoke()
                _uiState.update {
                    it.copy(list = list, isLoading = false)
                }
            } catch (e: Exception) {
                _effects.emit(FavoriteEffect.ShowError(e))
            }
        }
    }

    private fun onCourseUnLiked(courseId: Long) {
        viewModelScope.launch {
            try {
                val course = _uiState.value.list.find { it.id == courseId }
                course?.let {
                    toggleFavoriteUseCase(it)
                    getFavoriteList()
                }
            } catch (e: Exception) {
                _effects.emit(FavoriteEffect.ShowError(e))
            }
        }
    }
}