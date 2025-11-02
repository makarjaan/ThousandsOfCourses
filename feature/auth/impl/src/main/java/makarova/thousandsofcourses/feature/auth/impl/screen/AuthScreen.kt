package makarova.thousandsofcourses.feature.auth.impl.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.thousandsofcourses.designsystem.AppTheme
import makarova.thousandsofcourses.feature.auth.impl.R

@Composable
fun AuthScreen (
    modifier: Modifier,
) {
    Surface (
        modifier = modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 140.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(28.dp))

            Text (
                text = stringResource(R.string.auth_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111213)
@Composable
private fun LoginPreview() {
    AppTheme {
        AuthScreen(modifier = Modifier.padding(horizontal = 16.dp))
    }
}