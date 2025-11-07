package makarova.thousandsofcourses.feature.favorite.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.thousandsofcourses.feature.main.api.widget.Loading
import makarova.thousandsofcourses.designsystem.uicomponents.SnackbarError
import makarova.thousandsofcourses.feature.favorite.impl.presentation.FavoriteEffect
import makarova.thousandsofcourses.feature.favorite.impl.presentation.FavoriteViewModel
import makarova.thousandsofcourses.utils.R

@Composable
fun FavoriteRoute() {
    val viewModel: FavoriteViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val snackbar = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { eff ->
            when (eff) {
                is FavoriteEffect.ShowError -> {
                    val errorMessage =
                        eff.throwable.message ?: context.getString(R.string.error_unknown)
                    snackbar.showSnackbar(message = errorMessage)
                }
            }
        }
    }

    FavoriteScreen(
        onEvent = viewModel::reduce,
        courses = state.list
    )

    if (state.isLoading) {
        Loading()
    }

    SnackbarError(snackbar)
}