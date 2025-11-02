package makarova.thousandsofcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import makarova.thousandsofcourses.navigation.MainNavGraph
import makarova.thousandsofcourses.navigation.ScreenRegistration
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var screenRegistration: Set<@JvmSuppressWildcards ScreenRegistration>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainNavGraph(screenRegistration = screenRegistration)
        }
    }
}