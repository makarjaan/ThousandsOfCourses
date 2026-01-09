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
import makarova.citypulse.feature.auth.api.usecase.GetCurrentUserUseCase
import makarova.citypulse.feature.main.api.model.DetectCityResult
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.profile.api.usecase.UpdateUserAvatarUseCase
import makarova.citypulse.feature.profile.api.usecase.UpdateUserNameUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserAvatarUseCase: UpdateUserAvatarUseCase,
   // private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val detectCityUseCase: DetectCityUseCase,
  //  private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ProfileEffect>()
    val uiEffect: SharedFlow<ProfileEffect> = _uiEffect.asSharedFlow()

    init {
        loadProfile()
    }

    fun reduce(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LoadProfile -> loadProfile()
            ProfileEvent.Logout -> logout()
            ProfileEvent.OpenFavorites ->
                emitEffect(ProfileEffect.NavigateToFavorites)

            ProfileEvent.OpenCategories ->
                emitEffect(ProfileEffect.NavigateToCategories)

            ProfileEvent.ChangeCity ->
                emitEffect(ProfileEffect.NavigateToCityPicker)

            ProfileEvent.Back ->
                emitEffect(ProfileEffect.NavigateBack)

            is ProfileEvent.ChangeAvatar -> changeAvatar(event.uri)
            is ProfileEvent.ChangeName -> changeName(event.newName)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val user = getCurrentUserUseCase()
                val cityResult = detectCityUseCase.invoke()
                val city = when (cityResult) {
                    is DetectCityResult.Success -> cityResult.city.name
                    else -> "Не определен"
                }

                // val favorites = getFavoritesUseCase(user.login)

                _uiState.update {
                    it.copy(
                        userName = user.name,
                        avatarUrl = user.avatarUrl,
                        city = city,
                        // favoritesCount = favorites.size,
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
                val user = getCurrentUserUseCase() ?: return@launch

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

    private fun changeAvatar(avatarUri: String) {
        viewModelScope.launch {
            try {
                val user = getCurrentUserUseCase() ?: return@launch

                // Здесь можно обработать изображение (сжать, загрузить на сервер и т.д.)
                val avatarUrl = avatarUri // Временно используем URI как URL

                val success = updateUserAvatarUseCase(user.login, avatarUrl)

                if (success) {
                    _uiState.update { it.copy(avatarUrl = avatarUrl) }
                    emitEffect(ProfileEffect.ShowMessage("Фото обновлено"))
                } else {
                    emitEffect(ProfileEffect.ShowError("Не удалось обновить фото"))
                }

            } catch (e: Exception) {
                emitEffect(ProfileEffect.ShowError("Ошибка: ${e.message}"))
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
          //  logoutUseCase()
            emitEffect(ProfileEffect.Logout)
        }
    }

    private fun emitEffect(effect: ProfileEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
