package makarova.citypulse.feature.main.impl.permission

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import makarova.citypulse.feature.main.impl.R

@Composable
fun PermissionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { androidx.compose.material3.Text("Разрешение на геолокацию") },
        text = {
            androidx.compose.foundation.layout.Column {
                androidx.compose.material3.Text("Для определения вашего города нужен доступ к геолокации.")
                androidx.compose.foundation.layout.Spacer(
                    modifier = androidx.compose.ui.Modifier.height(8.dp)
                )
                androidx.compose.material3.Text("Мы используем эту информацию только для показа событий рядом с вами.")
            }
        },
        confirmButton = {
            androidx.compose.material3.Button(onClick = onConfirm) {
                androidx.compose.material3.Text(stringResource(R.string.text_permit))
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                androidx.compose.material3.Text(stringResource(R.string.text_cancel))
            }
        }
    )
}