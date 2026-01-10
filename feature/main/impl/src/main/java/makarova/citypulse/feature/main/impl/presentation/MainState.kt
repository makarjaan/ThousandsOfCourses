package makarova.citypulse.feature.main.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.citypulse.feature.main.api.model.CityModel
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.model.SearchResultModel

@Immutable
data class MainState(
    val city: CityModel = CityModel("msk", "Москва"),
    val recommendedEvents: List<EventModel> = emptyList(),
    val categoryEvents: List<EventModel> = emptyList(),
    val currentCategory: String? = null,
    val categoryPage: Int = 1,
    val searchPage: Int = 1,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchContentType: String? = null,
    val searchIsFree: Boolean? = null,
    val searchResults: List<SearchResultModel> = emptyList(),
    val isSearching: Boolean = false,
    val searchQuery: String = "",
) {
    val visibleEvents: List<EventModel>
        get() = if (currentCategory == null) recommendedEvents else categoryEvents
}


sealed interface MainEffect {
    data class ShowError(val message: String) : MainEffect
    data class ShowSearchError(val message: String) : MainEffect
}

sealed interface MainEvent {
    data object LoadInitial : MainEvent
    data object DetectCity: MainEvent
    data object LoadNextCategoryPage: MainEvent
    data class ChangeCity(val city: CityModel) : MainEvent
    data class ApplyCategoryFilter(val categories: Set<String>) : MainEvent
    data class UpdateSearchQuery(val query: String) : MainEvent
    data object ClearSearch : MainEvent
}