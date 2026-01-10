package makarova.citypulse.feature.favorite.api.repository

import makarova.citypulse.feature.main.api.model.EventModel

interface FavoriteEventsRepository {
    suspend fun toggleFavorite(event: EventModel, userEmail: String): Boolean
    suspend fun getFavoriteEventsByUser(userEmail: String): List<EventModel>
    suspend fun isFavorite(eventId: String, userEmail: String): Boolean
    suspend fun getFavoritesCount(userEmail: String): Int
}