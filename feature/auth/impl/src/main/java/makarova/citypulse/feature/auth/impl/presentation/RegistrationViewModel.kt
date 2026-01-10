package makarova.citypulse.feature.auth.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import makarova.citypulse.analytics.AnalyticsTracker
import makarova.citypulse.feature.auth.api.usecase.LoginUseCase
import makarova.citypulse.feature.auth.api.usecase.RegisterUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val analyticsTracker: AnalyticsTracker
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationState())
    val uiState: StateFlow<RegistrationState> = _uiState

    private val _uiEffect = MutableSharedFlow<RegistrationEffect>()
    val uiEffect: SharedFlow<RegistrationEffect> = _uiEffect


    init {
        analyticsTracker.trackScreen("RegisterScreen")
    }

    fun reduce(event: RegistrationEvent) {
        when (event) {

            is RegistrationEvent.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(email = event.value)
            }

            is RegistrationEvent.OnNameChange -> {
                _uiState.value = _uiState.value.copy(name = event.value)
            }

            is RegistrationEvent.OnPasswordChange -> {
                _uiState.value = _uiState.value.copy(password = event.value)
            }

            RegistrationEvent.OnRegistrationClick -> {
                register()
            }
        }
    }

    private fun register() {
        with(_uiState.value) {

            if (email.isBlank() || password.isBlank()) {
                emitEffect(
                    RegistrationEffect.ShowError("Заполните все поля")
                )
                return
            }

            _uiState.value = copy(isLoading = true)

            viewModelScope.launch {
                val success = registerUseCase(email = email, name = name, password = password)
                if (success) {
                    val loginSuccess = loginUseCase(email, password)
                    _uiState.value = copy(isLoading = false)
                    if (loginSuccess) {
                        emitEffect(RegistrationEffect.NavigateToMain)
                    } else {
                        emitEffect(RegistrationEffect.ShowError("Ошибка входа"))
                    }
                } else {
                    _uiState.value = copy(isLoading = false)
                    emitEffect(RegistrationEffect.ShowError("Пользователь уже существует"))
                }
            }


            _uiState.value = copy(isLoading = false)

            emitEffect(RegistrationEffect.NavigateToMain)
        }
    }

    private fun emitEffect(effect: RegistrationEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
