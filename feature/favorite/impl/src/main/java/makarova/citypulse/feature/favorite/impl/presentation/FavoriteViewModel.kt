package makarova.citypulse.feature.favorite.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.citypulse.feature.auth.api.usecase.GetCurrentUserUseCase
import makarova.citypulse.feature.favorite.api.usecase.GetFavoriteEventsUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteEventsUseCase: GetFavoriteEventsUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FavoriteEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadFavorites()
    }

    fun reduce(event: FavoriteEvent) {
        when (event) {
            FavoriteEvent.Load -> loadFavorites()
            FavoriteEvent.Back ->
                emitEffect(FavoriteEffect.NavigateBack)

            is FavoriteEvent.OpenEvent ->
                emitEffect(FavoriteEffect.NavigateToEventDetail(event.eventId))
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val user = getCurrentUserUseCase()
            val favorites = getFavoriteEventsUseCase(user.login)

            _state.update {
                it.copy(
                    events = favorites,
                    isLoading = false
                )
            }
        }
    }

    private fun emitEffect(effect: FavoriteEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
