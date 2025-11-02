package makarova.thousandsofcourses.feature.auth.impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import makarova.thousandsofcourses.feature.auth.api.usecase.LoginUserUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {



    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState

    private val _effects = MutableSharedFlow<AuthEffect>()
    val effects: SharedFlow<AuthEffect> = _effects

    fun reduce(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnAuthClick -> {
                onLoginClicked()
            }
            is AuthEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
            }
            is AuthEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
            }
        }
    }

    private fun onEmailChanged(login: String) {
        _uiState.update { it.copy(login = login, isEmailError = false) }
    }

    private fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, isPasswordError = false) }
    }

    private fun onLoginClicked() {

        val currentState = _uiState.value
        if (currentState.login.isBlank()) {
            _uiState.update { it.copy(isEmailError = true) }
            return
        }

        if (currentState.password.isBlank()) {
            _uiState.update { it.copy(isPasswordError = true) }
            return
        }

        viewModelScope.launch {
            try {
                loginUserUseCase.invoke(_uiState.value.login, _uiState.value.password)
            } catch (e: Exception) {
                _effects.emit(AuthEffect.ShowError(e))
            }
        }
    }
}