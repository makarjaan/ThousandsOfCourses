package makarova.citypulse.feature.main.api.model

import makarova.citypulse.utils.Constants

data class SearchResultModel(
    val id: String,
    val contentType: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val city: String,
    val isFree: Boolean,
    val dates: String,
    val address: String,
    val siteUrl: String?,
    val tags: List<String>,
    val favoritesCount: Int
) {
    companion object {
        val EMPTY = SearchResultModel(
            id = Constants.EMPTY_STRING,
            contentType = Constants.EMPTY_STRING,
            title = Constants.EMPTY_STRING,
            description = Constants.EMPTY_STRING,
            imageUrl = Constants.EMPTY_STRING,
            category = Constants.EMPTY_STRING,
            city = Constants.EMPTY_STRING,
            isFree = false,
            dates = Constants.EMPTY_STRING,
            address = Constants.EMPTY_STRING,
            siteUrl = null,
            tags = emptyList(),
            favoritesCount = 0
        )
    }
}