package makarova.citypulse.feature.profile.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Tune
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

@Composable
fun UIProfileScreen(
    userName: String = "Арина",
    cityName: String = "Москва",
    avatarUrl: String? = null,
    onChangeName: (String) -> Unit,
    favoritesCount: Int = 12,
    onChangeAvatar: () -> Unit,
    onFavoritesClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onChangeCityClick: () -> Unit,
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
            onAvatarClick = onChangeAvatar,
            onNameClick = { showChangeNameDialog = true }
        )

        Spacer(modifier = Modifier.height(24.dp))


        ProfileMenuItem(
            title = "Избранные события",
            subtitle = "$favoritesCount событий",
            icon = Icons.Default.FavoriteBorder,
            onClick = onFavoritesClick
        )

        ProfileMenuItem(
            title = "Категории интересов",
            subtitle = "Настрой рекомендации",
            icon = Icons.Default.Tune,
            onClick = onCategoriesClick
        )

        ProfileMenuItem(
            title = "Город",
            subtitle = cityName,
            icon = Icons.Default.LocationOn,
            onClick = onChangeCityClick
        )


        Spacer(modifier = Modifier.weight(1f))

        ProfileLogout(onClick = onLogoutClick)
    }
}

