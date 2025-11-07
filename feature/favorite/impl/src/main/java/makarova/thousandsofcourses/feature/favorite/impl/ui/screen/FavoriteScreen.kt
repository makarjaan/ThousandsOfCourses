package makarova.thousandsofcourses.feature.favorite.impl.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import makarova.thousandsofcourses.feature.main.api.model.CourseModel
import makarova.thousandsofcourses.feature.main.api.widget.CourseCard
import makarova.thousandsofcourses.feature.favorite.impl.R
import makarova.thousandsofcourses.feature.favorite.impl.presentation.FavoriteEvent

@Composable
fun FavoriteScreen(
    onEvent: (FavoriteEvent) -> Unit,
    courses: List<CourseModel>
) {
    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.text_favorites),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(
                count = courses.size,
                key = { index -> courses[index].id }
            ) { index ->
                CourseCard(
                    course = courses[index],
                    onCourseLiked = { onEvent(FavoriteEvent.OnCourseLiked(courses[index].id)) }
                )
            }
        }

    }
}
