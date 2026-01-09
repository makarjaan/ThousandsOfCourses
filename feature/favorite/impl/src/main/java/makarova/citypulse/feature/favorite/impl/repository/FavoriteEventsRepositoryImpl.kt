package makarova.citypulse.feature.favorite.impl.repository

import makarova.citypulse.database.dao.FavoriteEventsDao
import makarova.citypulse.database.mapper.FavoriteEventMapper
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class FavoriteEventsRepositoryImpl @Inject constructor(
    private val favoriteEventsDao: FavoriteEventsDao,
    private val mapper: FavoriteEventMapper
) : FavoriteEventsRepository {

    
    override suspend fun toggleFavorite(event: EventModel, userEmail: String): Boolean {
        val isFavorite = favoriteEventsDao.isFavorite(event.id, userEmail)
        
        if (isFavorite) {
            favoriteEventsDao.removeFromFavorites(event.id, userEmail)
            return false
        } else {
            val entity = mapper.mapToEntity(event, userEmail)
            favoriteEventsDao.insert(entity)
            return true
        }
    }
    
    override suspend fun getFavoriteEventsByUser(userEmail: String): List<EventModel> {
        val entities = favoriteEventsDao.getAllByUser(userEmail)
        return entities.map { mapper.mapToEventModel(it) }
    }
    
    override suspend fun isFavorite(eventId: String, userEmail: String): Boolean {
        return favoriteEventsDao.isFavorite(eventId, userEmail)
    }
    
    override suspend fun getFavoritesCount(userEmail: String): Int {
        return favoriteEventsDao.getCount(userEmail)
    }
}