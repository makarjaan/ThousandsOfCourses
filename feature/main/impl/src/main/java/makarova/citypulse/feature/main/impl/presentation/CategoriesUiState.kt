package makarova.citypulse.feature.main.impl.presentation

import androidx.compose.runtime.Immutable
import makarova.citypulse.feature.main.api.model.EventCategoryModel

@Immutable
data class CategoriesUiState(
    val categories: List<EventCategoryModel> = emptyList(),
    val selected: Set<String> = emptySet(),
    val isLoading: Boolean = false
)

sealed interface CategoriesEvent {
    data object LoadCategories : CategoriesEvent
    data class ToggleCategory(val slug: String) : CategoriesEvent
    data object ResetSelection : CategoriesEvent
}

