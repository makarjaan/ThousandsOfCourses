package makarova.citypulse.feature.auth.impl.ui.screen

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import makarova.citypulse.feature.auth.impl.presentation.RegistrationEffect
import makarova.citypulse.feature.auth.impl.presentation.RegistrationEvent
import makarova.citypulse.feature.auth.impl.presentation.RegistrationViewModel

@Composable
fun RegistrationScreen(
    onRegistrationSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is RegistrationEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message
                    )
                }
                RegistrationEffect.NavigateToMain -> {
                    onRegistrationSuccess()
                }
            }
        }
    }

    UIRegistrationScreen(
        modifier = modifier,
        email = state.email,
        name = state.name,
        password = state.password,
        isLoading = state.isLoading,
        onEmailChange = {
            viewModel.reduce(RegistrationEvent.OnEmailChange(it))
        },
        onNameChange = {
            viewModel.reduce(RegistrationEvent.OnNameChange(it))
        },
        onPasswordChange = {
            viewModel.reduce(RegistrationEvent.OnPasswordChange(it))
        },
        onRegisterClick = {
            viewModel.reduce(RegistrationEvent.OnRegistrationClick)
        },
        onNavigateToLogin = onNavigateToLogin,
        snackbarHostState = snackbarHostState
    )

    SnackbarHost(
        hostState = snackbarHostState,
    )
}
