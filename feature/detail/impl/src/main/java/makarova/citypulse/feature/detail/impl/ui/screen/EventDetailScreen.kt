package makarova.citypulse.feature.detail.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import makarova.citypulse.feature.detail.impl.presentation.EmptyState
import makarova.citypulse.feature.detail.impl.presentation.ErrorState
import makarova.citypulse.feature.detail.impl.presentation.EventDetailsEffect
import makarova.citypulse.feature.detail.impl.presentation.EventDetailsEvent
import makarova.citypulse.feature.detail.impl.presentation.EventDetailsViewModel

@Composable
fun EventDetailScreen(
    eventId: String,
    onBackClick: () -> Unit,
    onShare: () -> Unit
) {
    val viewModel: EventDetailsViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(eventId) {
        viewModel.reduce(EventDetailsEvent.LoadEvent(eventId))
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is EventDetailsEffect.ShowError -> {
                    snackbarHostState.showSnackbar(message = effect.message)
                }
                is EventDetailsEffect.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = effect.message)
                }
                is EventDetailsEffect.ShareEvent -> {
                    onShare()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                ErrorState(
                    message = state.error,
                    onRetry = { viewModel.reduce(EventDetailsEvent.LoadEvent(eventId)) },
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.event != null -> {
                UIEventDetailScreen(
                    event = state.event!!,
                    isFavorite = state.isFavorite,
                    onFavoriteClick = {
                        viewModel.reduce(EventDetailsEvent.ToggleFavorite)
                                      },
                    onBackClick = onBackClick
                )
            }

            else -> {
                EmptyState(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


