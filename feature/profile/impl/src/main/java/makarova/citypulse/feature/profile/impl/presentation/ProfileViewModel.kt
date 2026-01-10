package makarova.citypulse.feature.profile.impl.presentation

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
import makarova.citypulse.analytics.AnalyticsTracker
import makarova.citypulse.feature.auth.api.usecase.GetCurrentUserUseCase
import makarova.citypulse.feature.auth.api.usecase.LogoutUseCase
import makarova.citypulse.feature.favorite.api.usecase.GetFavoriteEventsUseCase
import makarova.citypulse.feature.favorite.api.usecase.GetFavoritesCountUseCase
import makarova.citypulse.feature.main.api.model.DetectCityResult
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.profile.api.usecase.GetTopCategoriesUseCase
import makarova.citypulse.feature.profile.api.usecase.UpdateUserNameUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val getFavoritesCountUseCase: GetFavoritesCountUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val detectCityUseCase: DetectCityUseCase,
    private val getTopCategoriesUseCase: GetTopCategoriesUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val analyticsTracker: AnalyticsTracker
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ProfileEffect>()
    val uiEffect: SharedFlow<ProfileEffect> = _uiEffect.asSharedFlow()

    init {
        loadProfile()
        analyticsTracker.trackScreen("ProfileScreen")
    }

    fun reduce(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LoadProfile -> loadProfile()

            ProfileEvent.Logout -> logout()

            ProfileEvent.OpenFavorites ->
                emitEffect(ProfileEffect.NavigateToFavorites)

            ProfileEvent.Back ->
                emitEffect(ProfileEffect.NavigateBack)

            is ProfileEvent.ChangeName -> changeName(event.newName)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val user = getCurrentUserUseCase()
                val topCategories = getTopCategoriesUseCase(user.login)
                val cityResult = detectCityUseCase.invoke()
                val city = when (cityResult) {
                    is DetectCityResult.Success -> cityResult.city.name
                    else -> "Не определен"
                }

                val favoritesCount = getFavoritesCountUseCase.invoke(user.login)

                _uiState.update {
                    it.copy(
                        userName = user.name,
                        city = city,
                        topCategories = topCategories,
                        favoritesCount = favoritesCount,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
                emitEffect(ProfileEffect.ShowError("Ошибка загрузки профиля: ${e.message}"))
            }
        }
    }

    private fun changeName(newName: String) {
        if (newName.isBlank()) return

        viewModelScope.launch {
            try {
                val user = getCurrentUserUseCase()

                val success = updateUserNameUseCase(user.login, newName)

                if (success) {
                    _uiState.update { it.copy(userName = newName) }
                    emitEffect(ProfileEffect.ShowMessage("Имя изменено"))
                } else {
                    emitEffect(ProfileEffect.ShowError("Не удалось изменить имя"))
                }
            } catch (e: Exception) {
                emitEffect(ProfileEffect.ShowError("Ошибка: ${e.message}"))
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            emitEffect(ProfileEffect.Logout)
            logoutUseCase()
        }
    }

    private fun emitEffect(effect: ProfileEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
