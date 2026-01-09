package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.database.dao.CategoryInterestDao
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.impl.utils.RecommendationScoreCalculator
import javax.inject.Inject

class RecommendationsRepositoryImpl @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val authRepository: AuthRepository,
    private val categoryDao: CategoryInterestDao,
    private val calculator: RecommendationScoreCalculator
): RecommendationsRepository {

    override suspend fun getRecommendedEvents(
        city: String,
        page: Int
    ): List<EventModel> {
        val events = eventsRepository.getEvents(city = city)
        val now = System.currentTimeMillis()

        val userCurrent = authRepository.getCurrentUser()

        val userPreferences = categoryDao.getAllForUser(userCurrent?.login ?: "")
            .associate { it.category.lowercase() to it.score }

        return events.map { event ->
            val categoryScore = userPreferences[event.category.lowercase()] ?: 0
            val eventScore = calculator.calculate(event, userPreferences, now)
            Pair(event, categoryScore + eventScore)
        }.sortedByDescending { it.second }
            .map { it.first }
            .take(10)
    }

    override suspend fun increaseCategoryScore(email: String, category: String) {
        categoryDao.incrementScore(email, category)
    }

    override suspend fun clearPreferences(email: String) {
        categoryDao.clearUserPreferences(email)
    }

}
