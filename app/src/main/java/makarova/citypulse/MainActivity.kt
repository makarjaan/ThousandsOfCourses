package makarova.citypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import makarova.citypulse.designsystem.ui.AppTheme
import makarova.citypulse.navigation.AppRoot

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trace = Firebase.performance.newTrace("app_start_trace")
        trace.start()

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) {
                    AppRoot()
                }
            }
        }
//        lifecycleScope.launch {
//            delay(5_000L)
//            throw IllegalStateException("TEST_CRUSH")
//        }

        lifecycleScope.launch {
            delay(1000)
            trace.stop()
        }
    }
}