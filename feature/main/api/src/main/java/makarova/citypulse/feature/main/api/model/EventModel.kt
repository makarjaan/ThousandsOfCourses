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
    val shortTitle: String,
    val description: String,
    val fullDescription: String,
    val price: String,
    val ageRestriction: String,
    val locationSlug: String?,
    val siteUrl: String?
) {
    companion object {
        val EMPTY = EventModel(
            id = Constants.EMPTY_STRING,
            title = Constants.EMPTY_STRING,
            dateStart = 0L,
            dateEnd = 0,
            place = Constants.EMPTY_STRING,
            address = Constants.EMPTY_STRING,
            imageUrl = Constants.EMPTY_STRING,
            category = Constants.EMPTY_STRING,
            isFree = false,
            favoritesCount = 0,
            city = Constants.EMPTY_STRING,
            shortTitle = Constants.EMPTY_STRING,
            description = Constants.EMPTY_STRING,
            fullDescription = Constants.EMPTY_STRING,
            price = Constants.EMPTY_STRING,
            ageRestriction = Constants.EMPTY_STRING,
            locationSlug = Constants.EMPTY_STRING,
            siteUrl = Constants.EMPTY_STRING
        )
    }
}