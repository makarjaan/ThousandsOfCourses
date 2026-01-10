package makarova.citypulse.feature.profile.impl.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileState(
    val userName: String = "",
    val city: String = "",
    val topCategories: List<String> = emptyList(),
    val favoritesCount: Int = 0,
    val isLoading: Boolean = false
)

sealed interface ProfileEvent {
    data object LoadProfile : ProfileEvent
    data object Back : ProfileEvent
    data object Logout : ProfileEvent

    data object OpenFavorites : ProfileEvent

    data class ChangeName(val newName: String): ProfileEvent
}

sealed interface ProfileEffect {
    data object NavigateBack : ProfileEffect
    data object NavigateToFavorites : ProfileEffect
    data object Logout : ProfileEffect

    data class ShowError(val message: String): ProfileEffect
    data class ShowMessage(val message: String): ProfileEffect
}