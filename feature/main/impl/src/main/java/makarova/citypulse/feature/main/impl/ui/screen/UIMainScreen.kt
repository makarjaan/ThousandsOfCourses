package makarova.citypulse.feature.main.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.citypulse.designsystem.ui.AppTheme
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.impl.presentation.CategoriesUiState
import makarova.citypulse.feature.main.impl.ui.components.CategorySection
import makarova.citypulse.feature.main.impl.ui.components.EventCard
import makarova.citypulse.feature.main.impl.ui.components.HomeHeader
import makarova.citypulse.feature.main.impl.ui.components.SearchBar
import makarova.citypulse.utils.Constants
import makarova.citypulse.feature.main.impl.R


@Composable
fun UIMainScreen(
    events: List<EventModel>,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState,
    categoriesState: CategoriesUiState,
    onCategoryToggle: (String) -> Unit,
    onEventClick: (EventModel) -> Unit = {},
    onResetClick: () -> Unit = {},
    onLoadNextPage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item { HomeHeader() }
            item { SearchBar() }

            item {
                CategorySection(
                    state = categoriesState,
                    onToggle = onCategoryToggle,
                    onResetClick = onResetClick
                )
            }

            item {
                val title =
                    if (categoriesState.selected.isEmpty())
                        stringResource(R.string.text_recommen)
                    else
                        stringResource(R.string.text_category)

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            items(events) { event ->
                EventCard(
                    event = event,
                    onClick = { onEventClick(event) }
                )
            }


            if (isLoading) {
                item { PaginationLoading() }
            }

            item {
                LaunchedEffect(events.size) {
                    if (events.isNotEmpty() && !isLoading) {
                        onLoadNextPage()
                    }
                }
            }

            if (!isLoading && events.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.text_null_category),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun PaginationLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(strokeWidth = 2.dp)
    }
}

private val FakeEvents = listOf(
    EventModel(
        dateStart = 4L,
        dateEnd = null,
        address = Constants.EMPTY_STRING,
        isFree = false,
        favoritesCount = 0,
        id = "1",
        title = "Jazz Night Live",
        place = "Powerhouse",
        imageUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4",
        category = "music",
        city = "Казань"
    ),
    EventModel(
        dateStart = 8L,
        dateEnd = null,
        isFree = false,
        address = Constants.EMPTY_STRING,
        favoritesCount = 0,
        id = "2",
        title = "Street Food Festival",
        place = "Парк Горького",
        imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836",
        category = "music",
        city = "Казань"
    ),
)

@Preview(showSystemUi = true)
@Composable
private fun HomePreview() {
    AppTheme {
        UIMainScreen(
            events = FakeEvents,
            isLoading = false,
            snackbarHostState = SnackbarHostState(),
            categoriesState = CategoriesUiState(),
            onEventClick = {},
            onResetClick = {},
            onCategoryToggle = {},
            onLoadNextPage = {}
        )
    }
}
