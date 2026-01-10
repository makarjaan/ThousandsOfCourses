package makarova.citypulse.feature.main.api.model

import makarova.citypulse.utils.Constants

data class EventCategoryModel(
    val slug: String,
    val name: String
) {
    companion object {
        val EMPTY = EventCategoryModel(
            slug = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING
        )
    }
}
