package makarova.thousandsofcourses.feature.auth.impl.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.thousandsofcourses.designsystem.AppTheme
import makarova.thousandsofcourses.feature.auth.impl.R
import makarova.thousandsofcourses.feature.auth.impl.ui.components.CustomTextField
import makarova.thousandsofcourses.feature.auth.impl.ui.components.SocialBtn
import makarova.thousandsofcourses.feature.auth.impl.ui.components.SocialButton
import makarova.thousandsofcourses.feature.auth.impl.presentation.AuthEvent
import makarova.thousandsofcourses.feature.auth.impl.presentation.AuthState


@Composable
fun AuthScreen(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 140.dp)
                .padding(horizontal = 16.dp)
                .imePadding(),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.auth_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.text_email),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 28.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = state.login,
                onValueChange = { onEvent(AuthEvent.OnEmailChanged(it)) },
                helpText = stringResource(R.string.tf_help_text_example)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = state.password,
                onValueChange = { onEvent(AuthEvent.OnPasswordChanged(it)) },
                helpText = stringResource(R.string.tf_help_text_password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onEvent(AuthEvent.OnAuthClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = stringResource(R.string.auth_title),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Text(
                    text = stringResource(R.string.text_no_acc),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.text_register),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.text_forgot_pass),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialButton(
                    onClick = {},
                    btn = SocialBtn.VK(Color(0xFF2683ED)),
                    icon = painterResource(R.drawable.vk),
                    modifier = Modifier
                        .weight(1f)
                )

                SocialButton(
                    onClick = {},
                    btn = SocialBtn.OK(
                        Brush.verticalGradient(
                            listOf(Color(0xFFF98509), Color(0xFFF95D00))
                        )
                    ),
                    icon = painterResource(R.drawable.ok),
                    modifier = Modifier
                        .weight(1f)
                )
            }

        }
    }
}


@Preview
@Composable
private fun AuthPreview() {
    AppTheme {
        AuthScreen(
            state = AuthState(login="demo@example.com"),
            onEvent = {}
        )
    }
}
