package makarova.thousandsofcourses.feature.favorite.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.thousandsofcourses.feature.main.api.model.CourseModel

@Immutable
data class FavoriteState (
    val list: List<CourseModel> = emptyList(),
    val isLoading: Boolean = false
)

sealed class FavoriteEvent {
    data class OnCourseLiked(val course: Long) : FavoriteEvent()
}

sealed class FavoriteEffect {
    data class ShowError(val throwable: Throwable) : FavoriteEffect()
}