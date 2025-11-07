package makarova.thousandsofcourses.feature.main.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.thousandsofcourses.feature.main.api.model.CourseModel

@Immutable
data class MainState (
    val list: List<CourseModel> = emptyList(),
    val originalList: List<CourseModel> = emptyList(),
    val isLoading: Boolean = false,
    val isSortedDescending: Boolean = false
)

sealed class MainEvent {
    object OnFilterClick: MainEvent()
    data class OnCourseLiked(val course: Long): MainEvent()
}

sealed class MainEffect {
    data class ShowError(val throwable: Throwable): MainEffect()
}