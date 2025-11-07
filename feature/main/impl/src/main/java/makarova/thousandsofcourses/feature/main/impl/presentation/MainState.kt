package makarova.thousandsofcourses.feature.main.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.thousandsofcourses.api.model.CourseModel

@Immutable
data class MainState (
    val list: List<CourseModel> = emptyList(),
    val isLoading: Boolean = false
)

sealed class MainEvent {
    object OnFilterClick: MainEvent()
    data class OnCourseLiked(val course: String): MainEvent()
}

sealed class MainEffect {
    data class ShowError(val throwable: Throwable): MainEffect()
}