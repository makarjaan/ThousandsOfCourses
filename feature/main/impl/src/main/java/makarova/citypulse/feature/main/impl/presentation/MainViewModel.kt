package makarova.citypulse.feature.main.impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import makarova.citypulse.feature.main.api.usecase.GetEventByCategoryUseCase
import makarova.citypulse.feature.main.api.usecase.GetRecommendedEventsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecommendedEventsUseCase: GetRecommendedEventsUseCase,
    private val getEventByCategoryUseCase: GetEventByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<MainEffect>()
    val uiEffect: SharedFlow<MainEffect> = _uiEffect.asSharedFlow()

    fun reduce(event: MainEvent) {
        when (event) {
            MainEvent.LoadInitial -> loadInitialData()
            is MainEvent.ChangeCity -> changeCity(event.city)
            is MainEvent.ApplyCategoryFilter -> applyCategoryFilter(event.categories)
            MainEvent.LoadNextCategoryPage -> loadNextCategoryPage()
            is MainEvent.EventOpened -> TODO()
        }
    }

    private fun loadInitialData() {
        if (_uiState.value.currentCategory == null) {
            loadRecommended()
        } else {
            loadCategoryPage(_uiState.value.currentCategory!!, 1)
        }
    }

    private fun loadRecommended() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val recommended = getRecommendedEventsUseCase(_uiState.value.city)
                _uiState.update {
                    it.copy(
                        recommendedEvents = recommended,
                        isLoading = false,
                        currentCategory = null,
                        categoryEvents = emptyList(),
                        categoryPage = 1
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Ошибка загрузки рекомендаций") }
            }
        }
    }

    private fun applyCategoryFilter(categories: Set<String>) {

        val newCategory = if (categories.isEmpty()) null else categories.first()
        if (newCategory == _uiState.value.currentCategory) {
            return
        }

        if (categories.isEmpty()) {
            _uiState.update {
                it.copy(
                    currentCategory = null,
                    categoryEvents = emptyList(),
                    categoryPage = 1,
                    isCategorySwitching = false
                )
            }
            return
        }

        val category = categories.first()
        _uiState.update {
            it.copy(
                currentCategory = category,
                categoryEvents = emptyList(),
                categoryPage = 1,
                isLoading = true,
                isCategorySwitching = true
            )
        }
        loadCategoryPage(category, 1)
    }

    private fun loadCategoryPage(category: String, page: Int) {
        viewModelScope.launch {
            try {
                val newEvents = getEventByCategoryUseCase(
                    city = _uiState.value.city,
                    category = category,
                    page = page
                )

                _uiState.update { state ->
                    val updatedEvents = if (page == 1) newEvents else state.categoryEvents + newEvents
                    state.copy(
                        categoryEvents = updatedEvents,
                        isLoading = false,
                        categoryPage = page,
                        isCategorySwitching = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Ошибка загрузки событий по категории",
                        isCategorySwitching = false
                    )
                }
            }
        }
    }

    private fun loadNextCategoryPage() {
        val category = _uiState.value.currentCategory ?: return
        val nextPage = _uiState.value.categoryPage + 1
        loadCategoryPage(category, nextPage)
    }

    private fun changeCity(city: String) {
        _uiState.update { it.copy(city = city) }
        loadInitialData()
    }
}

