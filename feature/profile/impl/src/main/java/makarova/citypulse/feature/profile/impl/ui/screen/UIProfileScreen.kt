package makarova.citypulse.feature.profile.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import makarova.citypulse.feature.profile.impl.ui.components.ChangeNameDialog
import makarova.citypulse.feature.profile.impl.ui.components.ProfileHeader
import makarova.citypulse.feature.profile.impl.ui.components.ProfileLogout
import makarova.citypulse.feature.profile.impl.ui.components.ProfileMenuItem
import makarova.citypulse.feature.profile.impl.ui.components.TopCategoriesRow

@Composable
fun UIProfileScreen(
    userName: String,
    cityName: String,
    onChangeName: (String) -> Unit,
    topCategories: List<String>,
    favoritesCount: Int,
    onFavoritesClick: () -> Unit,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    var showChangeNameDialog by remember { mutableStateOf(false) }

    if (showChangeNameDialog) {
        ChangeNameDialog(
            currentName = userName,
            onDismiss = { showChangeNameDialog = false },
            onSave = { newName ->
                onChangeName(newName)
                showChangeNameDialog = false
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ProfileHeader(
            userName = userName,
            cityName = cityName,
            onBackClick = onBackClick,
            onNameClick = { showChangeNameDialog = true }
        )

        Spacer(modifier = Modifier.height(24.dp))

        TopCategoriesRow(categories = topCategories)

        Spacer(modifier = Modifier.height(8.dp))

        ProfileMenuItem(
            title = "Избранные события",
            subtitle = "$favoritesCount событий",
            icon = Icons.Default.FavoriteBorder,
            onClick = onFavoritesClick
        )

        Spacer(modifier = Modifier.weight(1f))

        ProfileLogout(onClick = onLogoutClick)
    }
}

