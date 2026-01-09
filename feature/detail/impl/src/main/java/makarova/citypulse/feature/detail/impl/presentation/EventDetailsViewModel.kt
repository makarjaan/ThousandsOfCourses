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
import makarova.citypulse.feature.auth.api.usecase.GetCurrentUserUseCase
import makarova.citypulse.feature.detail.api.usecase.GetEventDetailsUseCase
import makarova.citypulse.feature.favorite.api.usecase.IsFavoriteUseCase
import makarova.citypulse.feature.favorite.api.usecase.ToggleFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
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
        }
    }

    private fun loadEvent(eventId: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }

                val event = getEventDetailsUseCase(eventId)

                val user = getCurrentUserUseCase()
                val isFavorite = if (user.login.isNotBlank()) {
                    isFavoriteUseCase.invoke(eventId, user.login)
                } else {
                    false
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        event = event,
                        isFavorite = isFavorite,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Ошибка загрузки события",
                    )
                }
                emitEffect(EventDetailsEffect.ShowError("Не удалось загрузить данные события"))
            }
        }
    }

    private fun toggleFavorite() {
        viewModelScope.launch {
            val currentEvent = _uiState.value.event ?: return@launch
            val user = getCurrentUserUseCase()

            if (user.login.isBlank()) {
                emitEffect(EventDetailsEffect.ShowError("Требуется авторизация"))
                return@launch
            }

            val wasFavorite = _uiState.value.isFavorite
            val newFavoriteState = !wasFavorite
            val newFavoritesCount = if (newFavoriteState) {
                currentEvent.favoritesCount + 1
            } else {
                maxOf(0, currentEvent.favoritesCount - 1)
            }

            _uiState.update { state ->
                state.copy(
                    isFavorite = newFavoriteState,
                    event = currentEvent.copy(favoritesCount = newFavoritesCount)
                )
            }

            try {
                toggleFavoriteUseCase.invoke(currentEvent, user.login)

                emitEffect(
                    EventDetailsEffect.ShowMessage(
                        if (newFavoriteState) "Добавлено в избранное" else "Удалено из избранного"
                    )
                )
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isFavorite = wasFavorite,
                        event = currentEvent
                    )
                }
                emitEffect(EventDetailsEffect.ShowError("Не удалось обновить избранное"))
            }
        }
    }

    private suspend fun emitEffect(effect: EventDetailsEffect) {
        _uiEffect.emit(effect)
    }

}
