package makarova.thousandsofcourses.feature.auth.impl.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


sealed class SocialBtn {
    data class VK(val color: Color): SocialBtn()
    data class OK(val brush: Brush): SocialBtn()
}

@Composable
fun SocialButton(
    onClick: () -> Unit,
    btn: SocialBtn,
    icon: Painter,
    modifier: Modifier = Modifier
) {

    val shape = RoundedCornerShape(30.dp)
    val (mod, container) = when (btn) {
        is SocialBtn.VK -> Modifier.clip(shape) to btn.color
        is SocialBtn.OK -> Modifier.clip(shape).background(btn.brush, shape) to Color.Transparent
    }

    Button(
        onClick = onClick,
        modifier = modifier.then(mod).height(40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = container),
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(height = 40.dp, width = 50.dp)
        )
    }
}