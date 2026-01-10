package makarova.citypulse.feature.favorite.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import makarova.citypulse.feature.favorite.impl.ui.components.FavoriteEventCard
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.favorite.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIFavoriteScreen(
    events: List<EventModel>,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onEventClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text(stringResource(R.string.fav_events)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            events.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.fav_empty))
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(events) { event ->
                        FavoriteEventCard(
                            event = event,
                            onClick = { onEventClick(event.id) }
                        )
                    }
                }
            }
        }
    }
}
