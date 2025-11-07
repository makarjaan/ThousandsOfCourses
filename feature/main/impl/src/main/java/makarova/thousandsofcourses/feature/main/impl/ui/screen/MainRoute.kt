package makarova.thousandsofcourses.feature.main.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.thousandsofcourses.designsystem.uicomponents.SnackbarError
import makarova.thousandsofcourses.feature.main.impl.presentation.MainEffect
import makarova.thousandsofcourses.feature.main.impl.presentation.MainViewModel
import makarova.thousandsofcourses.utils.R


@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    onFilterChanged: () -> Unit,
    onCourseLiked: (String) -> Unit
) {

    val viewModel: MainViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val snackbar = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { eff ->
            when (eff) {
                is MainEffect.ShowError -> {
                    val errorMessage =
                        eff.throwable.message ?: context.getString(R.string.error_unknown)
                    snackbar.showSnackbar(message = errorMessage)
                }
            }
        }
    }

    MainScreen(
        courses = state.list,
        onCourseLiked = onCourseLiked,
        onFilterClick = onFilterChanged
    )

    SnackbarError(snackbar)
}