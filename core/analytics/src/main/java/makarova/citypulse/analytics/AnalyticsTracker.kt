package makarova.citypulse.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsTracker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {

    fun trackScreen(screenName: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            androidx.core.os.bundleOf(
                FirebaseAnalytics.Param.SCREEN_NAME to screenName
            )
        )
    }
}
