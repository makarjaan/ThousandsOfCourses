package makarova.citypulse.feature.profile.impl.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.citypulse.feature.profile.impl.presentation.ProfileEvent
import makarova.citypulse.feature.profile.impl.presentation.ProfileViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import makarova.citypulse.feature.profile.impl.presentation.ProfileEffect

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onFavorites: () -> Unit,
    onLogout: () -> Unit,
) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.reduce(ProfileEvent.LoadProfile)
    }

    var effectHandlingCounter by remember { mutableStateOf(0) }

    LaunchedEffect(effectHandlingCounter) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                ProfileEffect.NavigateBack -> {
                    onBack()
                    effectHandlingCounter++
                }

                ProfileEffect.NavigateToFavorites -> {
                    onFavorites()
                    effectHandlingCounter++
                }

                ProfileEffect.Logout -> {
                    onLogout()
                    effectHandlingCounter++
                }

                is ProfileEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is ProfileEffect.ShowMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    UIProfileScreen(
        userName = state.userName,
        cityName = state.city,
        favoritesCount = state.favoritesCount,
        topCategories = state.topCategories,
        onFavoritesClick = {
            viewModel.reduce(ProfileEvent.OpenFavorites)
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
