package makarova.citypulse.feature.detail.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.citypulse.feature.main.api.model.EventModel

@Immutable
data class EventDetailsState(
    val isLoading: Boolean = false,
    val event: EventModel? = null,
    val isFavorite: Boolean = false,
    val error: String? = null,
)

sealed interface EventDetailsEvent {
    data class LoadEvent(val eventId: String) : EventDetailsEvent
    data object ToggleFavorite : EventDetailsEvent
}

sealed interface EventDetailsEffect {
    data class ShowError(val message: String) : EventDetailsEffect
    data class ShowMessage(val message: String) : EventDetailsEffect
}