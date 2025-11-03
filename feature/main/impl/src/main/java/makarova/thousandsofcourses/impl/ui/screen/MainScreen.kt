package makarova.thousandsofcourses.impl.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        SearchRow(onSearchClick, onFilterClick)
        Spacer(Modifier.height(8.dp))
        SortRow(text = "По дате добавления", accent = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(8.dp))


        val courses = remember { demoCourses() }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp, top = 8.dp)
        ) {
            items(courses) { course ->
                CourseCard(
                    course = course,
                    shape = cardShape,
                    accent = green,
                    onClick = { onCourseClick(course) }
                )
            }
        }
    }
}