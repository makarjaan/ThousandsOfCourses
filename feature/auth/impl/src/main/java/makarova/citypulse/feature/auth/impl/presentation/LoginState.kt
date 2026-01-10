package makarova.citypulse.feature.auth.impl.presentation

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface LoginEvent {
    data class OnEmailChange(val value: String) : LoginEvent
    data class OnPasswordChange(val value: String) : LoginEvent
    data object OnLoginClick : LoginEvent
}

sealed interface LoginEffect {
    data object NavigateToMain : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
