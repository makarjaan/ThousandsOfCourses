package makarova.citypulse.feature.main.impl.usecasae

import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.repository.CategoryInterestRepository
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.api.usecase.GetRecommendedEventsUseCase
import makarova.citypulse.feature.main.impl.recommendation.RecommendationScoreCalculator
import javax.inject.Inject

class GetRecommendedEventsUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val categoryInterestRepository: CategoryInterestRepository,
    private val calculator: RecommendationScoreCalculator
) : GetRecommendedEventsUseCase {

    override suspend fun invoke(city: String): List<EventModel> {

        val events = eventsRepository.getEvents(city)
        val now = System.currentTimeMillis()

        val userPreferences = categoryInterestRepository.getUserCategoryScores("arina@mail.ru")

        val categoryScores = if (userPreferences.isEmpty()) {
            events.associate { it.category.lowercase() to 0 }
        } else {
            userPreferences
        }


        return events
            .map { event ->
                val eventScore = calculator.calculate(
                    event = event,
                    categoryScores = categoryScores,
                    now = now
                )
                event to eventScore
            }
            .sortedByDescending { it.second }
            .map { it.first }
            .take(10)
    }
}

