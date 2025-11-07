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
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListOfCoursesUseCase: GetListOfCoursesUseCase
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
            is MainEvent.OnFilterClick -> { onFilterClick() }
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

    private fun onCourseLiked(course: String) {

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