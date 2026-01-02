package makarova.citypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import makarova.citypulse.designsystem.AppTheme
import makarova.citypulse.navigation.MainNavGraph
import makarova.citypulse.navigation.ScreenRegistration
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var screenRegistration: Set<@JvmSuppressWildcards ScreenRegistration>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainNavGraph(screenRegistration = screenRegistration)
            }
        }
    }
}