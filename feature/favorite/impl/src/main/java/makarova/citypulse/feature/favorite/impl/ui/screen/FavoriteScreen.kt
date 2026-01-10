package makarova.citypulse.feature.favorite.impl.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import makarova.citypulse.feature.favorite.impl.presentation.*

@Composable
fun FavoriteScreen(
    onBackClick: () -> Unit,
    onEventClick: (String) -> Unit
) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.reduce(FavoriteEvent.Load)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                FavoriteEffect.NavigateBack -> onBackClick()

                is FavoriteEffect.NavigateToEventDetail ->
                    onEventClick(effect.eventId)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        UIFavoriteScreen(
            events = state.events,
            isLoading = state.isLoading,
            onBackClick = {
                viewModel.reduce(FavoriteEvent.Back)
            },
            onEventClick = { eventId ->
                viewModel.reduce(FavoriteEvent.OpenEvent(eventId))
            }
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
