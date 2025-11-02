package makarova.thousandsofcourses.feature.auth.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import makarova.thousandsofcourses.feature.auth.impl.R
import makarova.thousandsofcourses.feature.auth.impl.presentation.AuthEffect
import makarova.thousandsofcourses.feature.auth.impl.presentation.AuthViewModel
import androidx.compose.runtime.getValue

@Composable
fun AuthRoute(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val snackbar = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { eff ->
            when (eff) {
                is AuthEffect.ShowError -> snackbar.showSnackbar(
                    context.getString(R.string.check_login)
                )
                AuthEffect.NavigateToNext -> onLoginSuccess()
            }
        }
    }

    AuthScreen(
        state = state,
        onEvent = viewModel::reduce,
        modifier = modifier
    )
}
