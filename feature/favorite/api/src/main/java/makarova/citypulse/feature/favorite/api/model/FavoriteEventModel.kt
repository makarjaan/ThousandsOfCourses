package makarova.citypulse.feature.favorite.api.model

import makarova.citypulse.utils.Constants

data class FavoriteEventModel(
    val eventId: String,
    val userEmail: String,
    val title: String,
    val imageUrl: String?,
    val place: String?,
    val dateStart: Long,
    val dateEnd: Long?,
    val isFree: Boolean,
    val city: String,
    val category: String,
    val price: String?,
    val addedAt: Long
) {
    companion object {
        val EMPTY = FavoriteEventModel(
            eventId = Constants.EMPTY_STRING,
            userEmail = Constants.EMPTY_STRING,
            title = Constants.EMPTY_STRING,
            imageUrl = null,
            place = null,
            dateStart = 0L,
            dateEnd = null,
            isFree = false,
            city = Constants.EMPTY_STRING,
            category = Constants.EMPTY_STRING,
            price = null,
            addedAt = 0L
        )
    }
}