package makarova.thousandsofcourses.feature.main.impl.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import makarova.thousandsofcourses.feature.main.impl.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.thousandsofcourses.designsystem.AppTheme
import androidx.compose.ui.res.painterResource
import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.feature.main.impl.ui.components.CourseCard
import makarova.thousandsofcourses.feature.main.impl.ui.components.SearchWithFilter


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
    courses: List<CourseModel>
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {

        SearchWithFilter()

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_filter),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(Modifier.width(4.dp))

            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_up),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.width(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(courses.size, key = { courses[it].id }) { index ->
                CourseCard(
                    course = courses[index],
                    onClick = {},
                    onBookmarkClick = {}
                )
            }
        }

    }
}



@Preview
@Composable
private fun MainPreview() {
    AppTheme {
        MainScreen(courses = demoCourses)
    }
}


val demoCourses = listOf(
    CourseModel(
        id = "1",
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки...",
        price= "999 ₽",
        startDate = "22 Мая 2024",
        rate = 4.9,
        publishDate = "22p",
        hasLike = false
    ),
    CourseModel(
        id = "2",
        title = "3D-дженералист",
        text = "Освой профессию 3D-дженералиста и стань универсальным специалистом...",
        price = "12 000 ₽",
        startDate = "10 Сентября 2024",
        rate = 3.9,
        publishDate = "22p",
        hasLike = false
    )
)