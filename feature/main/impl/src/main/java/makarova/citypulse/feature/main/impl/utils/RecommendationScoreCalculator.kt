package makarova.citypulse.feature.main.impl.recommendation

import makarova.citypulse.feature.main.api.model.EventModel
import kotlin.math.abs
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecommendationScoreCalculator @Inject constructor() {

    fun calculate(
        event: EventModel,
        categoryScores: Map<String, Int>,
        now: Long
    ): Int {
        var score = 0

        val categoryScore = categoryScores[event.category] ?: 0
        score += categoryScore * 5

        score += when {
            event.favoritesCount > 500 -> 40
            event.favoritesCount > 100 -> 20
            else -> 0
        }

        if (event.isFree) score += 30

        val daysDiff = TimeUnit.MILLISECONDS.toDays(
            abs(event.dateStart - now)
        )

        score += when {
            daysDiff == 0L -> 30
            daysDiff <= 3 -> 20
            daysDiff <= 7 -> 10
            else -> 0
        }

        if (event.imageUrl.isNotBlank()) score += 10
        if (event.place.isNotBlank() && event.address.isNotBlank()) score += 10

        return score
    }
}
