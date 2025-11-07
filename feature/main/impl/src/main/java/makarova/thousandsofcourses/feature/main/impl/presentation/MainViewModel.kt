package makarova.thousandsofcourses.feature.main.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.thousandsofcourses.api.usecase.GetListOfCoursesUseCase
import makarova.thousandsofcourses.api.usecase.ToggleFavoriteUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListOfCoursesUseCase: GetListOfCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState

    private val _effects = MutableSharedFlow<MainEffect>()
    val effects: SharedFlow<MainEffect> = _effects

    init {
        getCourseList()
    }

    fun reduce(event: MainEvent) {
        when (event) {
            is MainEvent.OnFilterClick -> onFilterClick()
            is MainEvent.OnCourseLiked -> onCourseLiked(event.course)
        }
    }

    private fun onFilterClick() {
        with(_uiState.value) {
            if (list.isEmpty()) return

            val sortedList = if (isSortedDescending) {
               originalList
            } else {
               list.sortedByDescending { course ->
                    course.publishDate
               }
            }

            _uiState.update {
                it.copy(list = sortedList, isSortedDescending = !isSortedDescending)
            }
        }
    }

    private fun onCourseLiked(courseId: Long) {
        viewModelScope.launch {
            try {
                val course = _uiState.value.list.find { it.id == courseId }
                course?.let {
                    toggleFavoriteUseCase(it)
                    updateCourseFavoriteStatus(courseId)
                }
            } catch (e: Exception) {
                _effects.emit(MainEffect.ShowError(e))
            }
        }
    }

    private suspend fun updateCourseFavoriteStatus(courseId: Long) {
        _uiState.update { state ->
            state.copy(
                list = state.list.map { course ->
                    if (course.id == courseId) {
                        course.copy(hasLike = !course.hasLike)
                    } else {
                        course
                    }
                },
                originalList = state.originalList.map { course ->
                    if (course.id == courseId) {
                        course.copy(hasLike = !course.hasLike)
                    } else {
                        course
                    }
                }
            )
        }
    }

    private fun getCourseList() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val list = getListOfCoursesUseCase.invoke()
                _uiState.update {
                    it.copy(list = list, originalList = list, isLoading = false)
                }
            } catch (e: Exception) {
                _effects.emit(MainEffect.ShowError(e))
            }
        }
    }
}