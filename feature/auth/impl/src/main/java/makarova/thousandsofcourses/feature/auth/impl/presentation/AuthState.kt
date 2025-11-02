package makarova.thousandsofcourses.feature.auth.impl.presentation

data class AuthState(
    val login: String = "",
    val password: String = "",
    val isEmailError: Boolean = false,
    val isEmailValid: Boolean = true,
    val isPasswordError: Boolean = false
)

sealed class AuthEvent {
    data class OnEmailChanged(val email: String): AuthEvent()
    data class OnPasswordChanged(val password: String): AuthEvent()
    object OnAuthClick: AuthEvent()
}

sealed class AuthEffect {
    object NavigateToNext: AuthEffect()
    data class ShowError(val throwable: Throwable): AuthEffect()
}