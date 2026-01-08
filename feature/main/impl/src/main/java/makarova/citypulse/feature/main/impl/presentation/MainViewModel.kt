package makarova.citypulse.feature.main.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import makarova.citypulse.feature.main.api.model.CityModel
import makarova.citypulse.feature.main.api.model.DetectCityResult
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.main.api.usecase.GetEventByCategoryUseCase
import makarova.citypulse.feature.main.api.usecase.GetRecommendedEventsUseCase
import makarova.citypulse.feature.main.api.usecase.SearchEventsUseCase
import makarova.citypulse.feature.main.impl.permission.DetectionResult
import makarova.citypulse.feature.main.impl.permission.DetectionResult.*
import makarova.citypulse.feature.main.impl.utils.AvailableCities
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecommendedEventsUseCase: GetRecommendedEventsUseCase,
    private val getEventByCategoryUseCase: GetEventByCategoryUseCase,
    private val searchEventsUseCase: SearchEventsUseCase,
    private val detectCityUseCase: DetectCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    private val _detectionResult = MutableSharedFlow<DetectionResult>()
    val detectionResult: SharedFlow<DetectionResult> = _detectionResult.asSharedFlow()

    private val _uiEffect = MutableSharedFlow<MainEffect>()
    val uiEffect: SharedFlow<MainEffect> = _uiEffect.asSharedFlow()

    private val searchQueryFlow = MutableStateFlow("")

    init {
        searchQueryFlow
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                when {
                    query.isEmpty() -> {
                        _uiState.update {
                            it.copy(
                                searchResults = emptyList(),
                                isSearching = false
                            )
                        }
                    }
                    query.length >= 2 -> {
                        performSearch(query)
                    }
                    else -> {
                        _uiState.update {
                            it.copy(
                                searchResults = emptyList(),
                                isSearching = false
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }


    fun reduce(event: MainEvent) {
        when (event) {
            MainEvent.LoadInitial -> loadInitialData()
            is MainEvent.ChangeCity -> changeCity(event.city)
            is MainEvent.ApplyCategoryFilter -> applyCategoryFilter(event.categories)
            MainEvent.LoadNextCategoryPage -> loadNextCategoryPage()
            MainEvent.DetectCity -> detectAndSetCity()
            MainEvent.ClearSearch -> clearSearch()
            is MainEvent.UpdateSearchQuery -> updateSearchQuery(event.query)
        }
    }

    private fun loadInitialData() {
        if (_uiState.value.city.slug == "msk") {
            loadRecommended()
        } else {
            loadRecommended()
        }
    }

    private fun loadRecommended() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                        categoryEvents = emptyList(),
                        categoryPage = 1
                    )
                }

                val events = getRecommendedEventsUseCase(_uiState.value.city.slug)

                _uiState.update {
                    it.copy(
                        recommendedEvents = events,
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                handleError("Ошибка загрузки рекомендаций")
            }
        }
    }

    private fun applyCategoryFilter(categories: Set<String>) {
        val newCategory = categories.firstOrNull()

        if (newCategory == null) {
            _uiState.update {
                it.copy(
                    currentCategory = null,
                    categoryEvents = emptyList(),
                    categoryPage = 1
                )
            }
            loadRecommended()
            return
        }

        if (newCategory == _uiState.value.currentCategory) return

        _uiState.update {
            it.copy(
                currentCategory = newCategory,
                categoryEvents = emptyList(),
                categoryPage = 1,
                isLoading = true,
                error = null
            )
        }

        loadCategoryPage(newCategory, 1)
    }

    private fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        searchQueryFlow.value = query
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isSearching = true, error = null)
            }

            try {
                val results = searchEventsUseCase(
                    query = query,
                    location = _uiState.value.city.slug
                )

                if (_uiState.value.searchQuery == query) {
                    _uiState.update {
                        it.copy(
                            searchResults = results,
                            isSearching = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isSearching = false,
                        error = "Ошибка поиска: ${e.message}"
                    )
                }
            }
        }
    }

    private fun clearSearch() {
        _uiState.update {
            it.copy(
                searchQuery = "",
                searchResults = emptyList(),
                isSearching = false
            )
        }
        searchQueryFlow.value = ""
    }

    fun applySearch(query: String) {
        updateSearchQuery(query)
    }

    private fun loadCategoryPage(category: String, page: Int) {
        viewModelScope.launch {
            try {
                val events = getEventByCategoryUseCase(
                    city = _uiState.value.city.slug,
                    category = category,
                    page = page
                )

                _uiState.update { state ->
                    state.copy(
                        categoryEvents =
                            if (page == 1) events
                            else state.categoryEvents + events,
                        categoryPage = page,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                handleError("Ошибка загрузки категории")
            }
        }
    }

    private fun loadNextCategoryPage() {
        val category = _uiState.value.currentCategory ?: return
        loadCategoryPage(category, _uiState.value.categoryPage + 1)
    }

    private fun changeCity(city: CityModel) {
        _uiState.update {
            it.copy(
                city = city,
                recommendedEvents = emptyList(),
                categoryEvents = emptyList(),
                currentCategory = null,
                categoryPage = 1
            )
        }
        loadRecommended()
    }

    fun detectAndSetCity() {
        viewModelScope.launch {
            try {
                when (val result = detectCityUseCase.invoke()) {
                    is DetectCityResult.PermissionRequired -> {
                        _detectionResult.emit(PermissionRequired)
                    }

                    is DetectCityResult.Success -> {
                        changeCity(result.city)
                        _detectionResult.emit(Success(result.city))
                    }

                    is DetectCityResult.NotFound -> {
                        val defaultCity = AvailableCities.first { it.slug == "msk" }
                        changeCity(defaultCity)
                        _detectionResult.emit(Error("Не удалось определить ваш город. Показана Москва."))
                    }

                    is DetectCityResult.Error -> {
                        _detectionResult.emit(Error(result.message))
                    }

                    null -> TODO()
                }
            } catch (e: Exception) {
                _detectionResult.emit(Error("Ошибка определения города: ${e.message}"))
            }
        }
    }

    private fun handleError(message: String) {
        _uiState.update {
            it.copy(isLoading = false, error = message)
        }
        viewModelScope.launch {
            _uiEffect.emit(MainEffect.ShowError(message))
        }
    }
}