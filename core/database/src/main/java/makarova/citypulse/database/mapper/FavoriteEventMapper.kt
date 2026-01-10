package makarova.citypulse.database.mapper

import makarova.citypulse.database.entities.FavoriteEventEntity
import makarova.citypulse.feature.favorite.api.model.FavoriteEventModel
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class FavoriteEventMapper @Inject constructor() {
    
    fun mapToEntity(event: EventModel, userEmail: String): FavoriteEventEntity {
        return FavoriteEventEntity(
            eventId = event.id,
            userEmail = userEmail,
            title = event.title,
            imageUrl = event.imageUrl,
            place = event.place,
            dateStart = event.dateStart,
            dateEnd = event.dateEnd,
            isFree = event.isFree,
            city = event.city,
            category = event.category,
            price = event.price,
            addedAt = System.currentTimeMillis()
        )
    }
    
    fun mapToModel(entity: FavoriteEventEntity): FavoriteEventModel {
        return FavoriteEventModel(
            eventId = entity.eventId,
            userEmail = entity.userEmail,
            title = entity.title,
            imageUrl = entity.imageUrl,
            place = entity.place,
            dateStart = entity.dateStart,
            dateEnd = entity.dateEnd,
            isFree = entity.isFree,
            city = entity.city,
            category = entity.category,
            price = entity.price,
            addedAt = entity.addedAt
        )
    }
    
    fun mapToEventModel(entity: FavoriteEventEntity): EventModel {
        return EventModel(
            id = entity.eventId,
            title = entity.title,
            dateStart = entity.dateStart,
            dateEnd = entity.dateEnd,
            place = entity.place ?: "",
            address = "",
            imageUrl = entity.imageUrl ?: "",
            category = entity.category,
            isFree = entity.isFree,
            favoritesCount = 0,
            city = entity.city,
            shortTitle = entity.title,
            description = "",
            fullDescription = "",
            price = entity.price ?: "",
            ageRestriction = "",
            locationSlug = "",
            siteUrl = null
        )
    }
}