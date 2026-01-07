package makarova.citypulse.feature.main.impl.ui.screen

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.delay
import makarova.citypulse.feature.main.impl.presentation.*

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val categoriesViewModel: CategoriesViewModel = hiltViewModel()
    val categoriesState by categoriesViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MainEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    LaunchedEffect(categoriesState.selected) {
        viewModel.reduce(MainEvent.ApplyCategoryFilter(categoriesState.selected))
    }


    LaunchedEffect(Unit) {
        viewModel.reduce(MainEvent.LoadInitial)
        categoriesViewModel.reduce(CategoriesEvent.LoadCategories)
    }

    LaunchedEffect(categoriesState.selected) {
        delay(100)
        viewModel.reduce(MainEvent.ApplyCategoryFilter(categoriesState.selected))
    }

    UIMainScreen(
        events = state.displayedEvents,
        isLoading = state.isLoading,
        snackbarHostState = snackbarHostState,
        categoriesState = categoriesState,
        onCategoryToggle = {
            categoriesViewModel.reduce(CategoriesEvent.ToggleCategory(it))
        },
        onResetClick = {
            categoriesViewModel.reduce(CategoriesEvent.ResetSelection)
            viewModel.reduce(MainEvent.LoadInitial)
        },
        onEventClick = {
            viewModel.reduce(MainEvent.EventOpened(it))
        },
        onLoadNextPage = {
            viewModel.reduce(MainEvent.LoadNextCategoryPage)
        }
    )
}
