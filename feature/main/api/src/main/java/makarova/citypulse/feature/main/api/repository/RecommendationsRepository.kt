package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.EventModel

interface RecommendationsRepository {

    suspend fun getRecommendedEvents(city: String, page: Int = 1): List<EventModel>

    suspend fun increaseCategoryScore(email: String, category: String)

    suspend fun clearPreferences(email: String)

}