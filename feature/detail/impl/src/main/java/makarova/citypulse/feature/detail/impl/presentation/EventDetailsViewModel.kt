package makarova.citypulse.feature.detail.impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.citypulse.feature.detail.api.usecase.GetEventDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailsUseCase: GetEventDetailsUseCase
) : ViewModel() {

    val eventId: String = savedStateHandle.get<String>("eventId") ?: ""

    private val _uiState = MutableStateFlow(EventDetailsState())
    val uiState: StateFlow<EventDetailsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<EventDetailsEffect>()
    val uiEffect: SharedFlow<EventDetailsEffect> = _uiEffect.asSharedFlow()

    init {
        if (eventId.isNotBlank()) {
            loadEvent(eventId)
        }
    }

    fun reduce(event: EventDetailsEvent) {
        when (event) {
            is EventDetailsEvent.LoadEvent -> loadEvent(event.eventId)
            EventDetailsEvent.ToggleFavorite -> toggleFavorite()
            EventDetailsEvent.ShareEvent -> shareEvent()
        }
    }

    private fun loadEvent(eventId: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }
                val event = getEventDetailsUseCase(eventId)
                _uiState.update { it.copy(isLoading = false, event = event) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Ошибка загрузки события"
                    )
                }
                _uiEffect.emit(EventDetailsEffect
                    .ShowError("Не удалось загрузить данные события"))
            }
        }
    }

    private fun toggleFavorite() {
        val currentEvent = _uiState.value.event
        if (currentEvent != null) {
            // Обновляем состояние в UI (реальная логика будет в репозитории)
            _uiState.update { state ->
                state.copy(
                    event = currentEvent.copy(
                        favoritesCount = if (state.isFavorite) {
                            currentEvent.favoritesCount - 1
                        } else {
                            currentEvent.favoritesCount + 1
                        }
                    ),
                    isFavorite = !state.isFavorite
                )
            }

            viewModelScope.launch {
                // Здесь будет вызов use case для обновления в базе данных
                _uiEffect.emit(
                    if (_uiState.value.isFavorite) {
                        EventDetailsEffect.ShowMessage("Добавлено в избранное")
                    } else {
                        EventDetailsEffect.ShowMessage("Удалено из избранного")
                    }
                )
            }
        }
    }

    private fun shareEvent() {
        val event = _uiState.value.event
        if (event != null) {
            viewModelScope.launch {
                _uiEffect.emit(EventDetailsEffect.ShareEvent(event))
            }
        }
    }

}
