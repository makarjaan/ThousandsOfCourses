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
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val analyticsTracker: AnalyticsTracker,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    private val _uiEffect = MutableSharedFlow<LoginEffect>()
    val uiEffect: SharedFlow<LoginEffect> = _uiEffect

    init {
        analyticsTracker.trackScreen("LoginScreen")
    }

    fun reduce(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(email = event.value)
            }

            is LoginEvent.OnPasswordChange -> {
                _uiState.value = _uiState.value.copy(password = event.value)
            }

            LoginEvent.OnLoginClick -> {
                login()
            }
        }
    }

    private fun login() {
        with(_uiState.value) {
            if (email.isBlank() || password.isBlank()) {
                emitEffect(LoginEffect.ShowError("Введите email и пароль"))
                return
            }

            _uiState.value = copy(isLoading = true)

            viewModelScope.launch {
                try {
                    val success = loginUseCase(email, password)
                    _uiState.value = _uiState.value.copy(isLoading = false)

                    if (success) {
                        emitEffect(LoginEffect.NavigateToMain)
                    } else {
                        emitEffect(LoginEffect.ShowError("Неверный email или пароль"))
                    }
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    emitEffect(LoginEffect.ShowError("Произошла ошибка: ${e.message}"))
                }
            }
        }
    }

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
