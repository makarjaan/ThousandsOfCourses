package makarova.citypulse.feature.main.impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.citypulse.feature.main.api.usecase.GetEventCategoriesUseCase
import makarova.citypulse.feature.main.api.usecase.IncreaseCategoryScoreUseCase
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetEventCategoriesUseCase,
    private val increaseCategoryScoreUseCase: IncreaseCategoryScoreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesUiState())
    val state: StateFlow<CategoriesUiState> = _state

    fun reduce(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.LoadCategories -> load()
            is CategoriesEvent.ToggleCategory -> toggle(event.slug)
            is CategoriesEvent.ResetSelection -> reset()
        }
    }

    private fun load() = viewModelScope.launch {
        val categories = getCategoriesUseCase()
        _state.update { it.copy(categories = categories) }
    }

    private fun toggle(slug: String) {
        val current = _state.value.selected.firstOrNull()

        val newSelected = when {
            current == slug -> emptySet()
            else -> setOf(slug)
        }

        _state.update { it.copy(selected = newSelected) }

        viewModelScope.launch {
            increaseCategoryScoreUseCase(
                email = "arina@mail.ru",
                category = slug
            )
        }
    }


    private fun reset() {
        _state.update { it.copy(selected = emptySet()) }
    }
}
