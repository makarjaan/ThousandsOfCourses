package makarova.citypulse.feature.main.impl.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import makarova.citypulse.feature.main.api.model.EventModel

@Immutable
data class MainState(
    val city: String = "msk",
    val allEvents: List<EventModel> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val recommendedEvents: List<EventModel> = emptyList(),
    val categoryEvents: List<EventModel> = emptyList(),
    val currentCategory: String? = null,
    val categoryPage: Int = 1,
    val isCategorySwitching: Boolean = false
) {
    val displayedEvents: List<EventModel>
        get() {
            return when {
                isCategorySwitching -> emptyList()
                currentCategory == null -> recommendedEvents
                else -> categoryEvents
            }
        }
}

sealed interface MainEffect {
    data class ShowError(val message: String) : MainEffect
}

sealed interface MainEvent {
    data object LoadInitial : MainEvent
    data object LoadNextCategoryPage: MainEvent
    data class ChangeCity(val city: String) : MainEvent
    data class ApplyCategoryFilter(val categories: Set<String>) : MainEvent
    data class EventOpened(val event: EventModel) : MainEvent
}