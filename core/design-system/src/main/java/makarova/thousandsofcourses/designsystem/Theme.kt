package makarova.thousandsofcourses.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        background = background,
        onBackground = onBackground,
        primary = primary,
        primaryContainer = primaryContainer,
        surface = surface,
        outline = outline
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

