package makarova.citypulse.feature.main.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.citypulse.feature.main.impl.permission.DetectionResult
import makarova.citypulse.feature.main.impl.permission.PermissionDialog
import makarova.citypulse.feature.main.impl.permission.hasLocationPermission
import makarova.citypulse.feature.main.impl.permission.rememberLocationPermissionLauncher
import makarova.citypulse.feature.main.impl.presentation.*
import makarova.citypulse.feature.main.impl.ui.components.CityPickerBottomSheet

@Composable
fun MainScreen(
    onEventClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val categoriesViewModel: CategoriesViewModel = hiltViewModel()
    val categoriesState by categoriesViewModel.state.collectAsState()

    var showCityPicker by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    var showPermissionDeniedSnackbar by remember { mutableStateOf(false) }

    val requestLocationPermission = rememberLocationPermissionLauncher(
        onGranted = {
            viewModel.detectAndSetCity()
        },
        onDenied = {
            showPermissionDeniedSnackbar = true
        }
    )

    if (showPermissionDeniedSnackbar) {
        LaunchedEffect(showPermissionDeniedSnackbar) {
            snackbarHostState.showSnackbar("Разрешение отклонено. Выберите город вручную.")
            showPermissionDeniedSnackbar = false
        }
    }

    val onDetectCityClick = {
        if (context.hasLocationPermission()) {
            viewModel.detectAndSetCity()
            showCityPicker = false
        } else {
            showPermissionDialog = true
        }
    }

    if (showPermissionDialog) {
        PermissionDialog(
            onDismiss = { showPermissionDialog = false },
            onConfirm = {
                showPermissionDialog = false
                requestLocationPermission()
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.detectionResult.collect { result ->
            when (result) {
                is DetectionResult.Success -> {
                    snackbarHostState.showSnackbar("Город определен: ${result.city.name}")
                }
                is DetectionResult.Error -> {
                    snackbarHostState.showSnackbar(result.message)
                }
                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MainEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is MainEffect.ShowSearchError -> {
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

    UIMainScreen(
        events = state.visibleEvents,
        isLoading = state.isLoading,
        city = state.city,
        searchResults = state.searchResults,
        searchQuery = state.searchQuery,
        snackbarHostState = snackbarHostState,
        categoriesState = categoriesState,
        onCategoryToggle = {
            categoriesViewModel.reduce(CategoriesEvent.ToggleCategory(it))
        },
        onEventClick = onEventClick,
        onCitySelected = { city ->
            viewModel.reduce(MainEvent.ChangeCity(city))
        },
        onLoadNextPage = {
            viewModel.reduce(MainEvent.LoadNextCategoryPage)
        },
        onDetectCityClick = onDetectCityClick,
        onSearchChanged = { query ->
            viewModel.applySearch(query)
        },
        onClearSearch = {
            viewModel.reduce(MainEvent.ClearSearch)
        },
        onProfileClick = { onProfileClick() },
        onCityPickerClick = { showCityPicker = true }
    )

    if (showCityPicker) {
        CityPickerBottomSheet(
            currentCity = state.city,
            onCitySelected = { selectedCity ->
                viewModel.reduce(MainEvent.ChangeCity(selectedCity))
                showCityPicker = false
            },
            onDetectCity = onDetectCityClick,
            onDismiss = { showCityPicker = false }
        )
    }
}