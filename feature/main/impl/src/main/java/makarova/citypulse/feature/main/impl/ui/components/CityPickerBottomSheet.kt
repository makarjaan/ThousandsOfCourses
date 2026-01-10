package makarova.citypulse.feature.main.impl.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import makarova.citypulse.feature.main.api.model.CityModel
import makarova.citypulse.feature.main.impl.R
import makarova.citypulse.feature.main.impl.utils.AvailableCities

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPickerBottomSheet(
    currentCity: CityModel?,
    onCitySelected: (CityModel) -> Unit,
    onDetectCity: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.padding(16.dp)) {

            Text(
                text = stringResource(R.string.text_choose_city),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onDetectCity,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.text_auto))
            }

            Spacer(Modifier.height(16.dp))

            AvailableCities.forEach { city ->
                CityRow(
                    city = city.name,
                    selected = city == currentCity,
                    onClick = { onCitySelected(city) }
                )
            }
        }
    }
}


@Composable
private fun CityRow(
    city: String,
    selected: Boolean = false,
    isAuto: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = city,
            style = if (isAuto) MaterialTheme.typography.bodyLarge
            else MaterialTheme.typography.bodyMedium,
            color = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onBackground
        )
    }
}
