package makarova.citypulse.feature.main.api.model

import makarova.citypulse.utils.Constants

data class EventModel (
    val id: String,
    val title: String,
    val dateStart: Long,
    val dateEnd: Long?,
    val place: String,
    val address: String,
    val imageUrl: String,
    val category: String,
    val isFree: Boolean,
    val favoritesCount: Int,
    val city: String,
) {
    companion object {
        val EMPTY = EventModel(
            id = Constants.EMPTY_STRING,
            title = Constants.EMPTY_STRING,
            dateStart = 0L,
            dateEnd = null,
            place = Constants.EMPTY_STRING,
            address = Constants.EMPTY_STRING,
            imageUrl = Constants.EMPTY_STRING,
            category = Constants.EMPTY_STRING,
            isFree = false,
            favoritesCount = 0,
            city = Constants.EMPTY_STRING
        )
    }
}