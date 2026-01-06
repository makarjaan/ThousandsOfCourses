package makarova.citypulse.feature.auth.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import makarova.citypulse.feature.auth.impl.presentation.LoginEffect
import makarova.citypulse.feature.auth.impl.presentation.LoginEvent
import makarova.citypulse.feature.auth.impl.presentation.LoginViewModel


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: LoginViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is LoginEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message
                    )
                }

                LoginEffect.NavigateToMain -> {
                    onLoginSuccess()
                }
            }
        }
    }

    UILoginScreen(
        email = state.email,
        password = state.password,
        isLoading = state.isLoading,
        onEmailChange = { viewModel.reduce(LoginEvent.OnEmailChange(it)) },
        onPasswordChange = { viewModel.reduce(LoginEvent.OnPasswordChange(it)) },
        onLoginClick = {
            viewModel.reduce(LoginEvent.OnLoginClick)
            onLoginSuccess },
        onNavigateToRegistration = onNavigateToRegistration,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}
