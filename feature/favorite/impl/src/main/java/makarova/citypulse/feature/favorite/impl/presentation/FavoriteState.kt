package makarova.citypulse.feature.favorite.impl.presentation

import makarova.citypulse.feature.main.api.model.EventModel

data class FavoriteState(
    val isLoading: Boolean = false,
    val events: List<EventModel> = emptyList()
)

sealed interface FavoriteEvent {
    object Load : FavoriteEvent
    object Back : FavoriteEvent
    data class OpenEvent(val eventId: String) : FavoriteEvent
}

sealed interface FavoriteEffect {
    object NavigateBack : FavoriteEffect
    data class NavigateToEventDetail(val eventId: String) : FavoriteEffect
}