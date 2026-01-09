package makarova.citypulse.feature.profile.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.citypulse.feature.profile.impl.presentation.ProfileEvent
import makarova.citypulse.feature.profile.impl.presentation.ProfileViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import makarova.citypulse.feature.profile.impl.presentation.ProfileEffect

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onFavorites: () -> Unit,
    onCategories: () -> Unit,
    onCityPicker: () -> Unit,
    onLogout: () -> Unit,
) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.reduce(ProfileEvent.LoadProfile)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                ProfileEffect.NavigateBack -> onBack()
                ProfileEffect.NavigateToFavorites -> onFavorites()
                ProfileEffect.NavigateToCategories -> onCategories()
                ProfileEffect.NavigateToCityPicker -> onCityPicker()
                ProfileEffect.Logout -> onLogout()
                is ProfileEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is ProfileEffect.ShowMessage ->  {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    UIProfileScreen(
        userName = state.userName,
        avatarUrl = state.avatarUrl,
        cityName = state.city,
        favoritesCount = state.favoritesCount,
        onChangeAvatar = {
            viewModel.reduce(ProfileEvent.ChangeAvatar(state.avatarUrl.toString()))
        },
        onFavoritesClick = {
            viewModel.reduce(ProfileEvent.OpenFavorites)
        },
        onCategoriesClick = {
            viewModel.reduce(ProfileEvent.OpenCategories)
        },
        onChangeCityClick = {
            viewModel.reduce(ProfileEvent.ChangeCity)
        },
        onBackClick = {
            viewModel.reduce(ProfileEvent.Back)
        },
        onChangeName = { newName ->
            viewModel.reduce(ProfileEvent.ChangeName(newName))
        },
        onLogoutClick = {
            viewModel.reduce(ProfileEvent.Logout)
        }
    )
}
