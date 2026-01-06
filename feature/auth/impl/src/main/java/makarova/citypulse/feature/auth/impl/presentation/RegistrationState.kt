package makarova.citypulse.feature.auth.impl.presentation

data class RegistrationState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface RegistrationEvent {
    data class OnEmailChange(val value: String) : RegistrationEvent
    data class OnNameChange(val value: String) : RegistrationEvent
    data class OnPasswordChange(val value: String) : RegistrationEvent
    data object OnRegistrationClick : RegistrationEvent
}

sealed interface RegistrationEffect {
    data object NavigateToMain : RegistrationEffect
    data class ShowError(val message: String) : RegistrationEffect
}
