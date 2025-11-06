package makarova.thousandsofcourses.feature.main.impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.feature.main.impl.R
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.stringResource


@Composable
fun CourseCard(
    course: CourseModel,
    onClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
        tonalElevation = 1.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.matchParentSize()
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingChip(course.rate)
                    DateChip(course.startDate)
                }

                GlassCircleButton(
                    onClick = onBookmarkClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_bookmark),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = course.text,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = course.price,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    TextButton(onClick = onClick) {
                        Text(
                            text = stringResource(R.string.text_more),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_right_short_fill),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun GlassBox(
    shape: Shape,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues =
        PaddingValues(horizontal = 6.dp, vertical = 4.dp),
    content: @Composable RowScope.() -> Unit
) {
    val brush = Brush.linearGradient(
        listOf(
            Color(0x4D32333A),
            Color(0x3332333A)
        )
    )
    Row(
        modifier = modifier
            .clip(shape)
            .background(brush, shape)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
private fun GlassCircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    val brush = Brush.linearGradient(
        listOf(Color(0x4D32333A), Color(0x3332333A))
    )
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(brush, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) { icon() }
}

@Composable
private fun RatingChip(rating: Double) {
    GlassBox(shape = RoundedCornerShape(12.dp)) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = rating.toString(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun DateChip(startDate: String) {
    GlassBox(shape = RoundedCornerShape(12.dp)) {
        Text(
            text = startDate,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Preview
@Composable
private fun PreviewCourses() {
    CourseCard(
        course = CourseModel(
            id = "1",
            title = "Java-разработчик с нуля",
            text = "Освойте backend-разработку и программирование на Java, фреймворки...",
            price = "999 ₽",
            startDate = "22 Мая 2024",
            rate = 4.9,
            hasLike = false,
            publishDate = "22222"),
        onClick = {},
        onBookmarkClick = {}
    )
}