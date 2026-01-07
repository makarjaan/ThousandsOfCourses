package makarova.citypulse.network.mapper

import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.network.pojo.response.KudagoCategoryResponse
import makarova.citypulse.network.pojo.response.KudagoDateResponse
import makarova.citypulse.network.pojo.response.KudagoEventResponse
import makarova.citypulse.network.pojo.response.KudagoEventsResponse
import makarova.citypulse.network.pojo.response.KudagoSearchItemResponse
import makarova.citypulse.network.pojo.response.KudagoSearchResponse
import makarova.citypulse.utils.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class KudagoResponseMapper @Inject constructor() {

    fun mapEventsResponse(input: KudagoEventsResponse?): List<EventModel> {
        return input?.results?.map { mapEvent(it) } ?: emptyList()
    }

    private fun mapEvent(input: KudagoEventResponse?): EventModel {
        val date = input?.dates?.firstOrNull()

        return input?.let {
            EventModel(
                id = it.id?.toString() ?: Constants.EMPTY_STRING,
                title = it.title ?: Constants.EMPTY_STRING,
                dateStart = date?.start ?: 0L,
                dateEnd = date?.end,
                place = it.place?.title ?: Constants.EMPTY_STRING,
                address = it.place?.address ?: Constants.EMPTY_STRING,
                imageUrl = it.images?.firstOrNull()?.image ?: Constants.EMPTY_STRING,
                category = it.categories?.firstOrNull() ?: Constants.EMPTY_STRING,
                isFree = it.isFree == true,
                favoritesCount = it.favoritesCount ?: 0,
                city = it.location?.name ?: Constants.EMPTY_STRING
            )
        } ?: EventModel.EMPTY
    }

    fun mapCategories(input: List<KudagoCategoryResponse>?): List<EventCategoryModel> {
        return input?.map { EventCategoryModel(slug = it.slug, name = it.name) } ?: emptyList()
    }

    fun mapSearchResponse(input: KudagoSearchResponse?): List<SearchResultModel> {
        return input?.results?.map { mapSearchItem(it) } ?: emptyList()
    }

    private fun mapSearchItem(input: KudagoSearchItemResponse?): SearchResultModel {
        return input?.let {
            SearchResultModel(
                id = it.id?.toString() ?: Constants.EMPTY_STRING,
                contentType = it.contentType ?: Constants.EMPTY_STRING,
                title = it.title ?: Constants.EMPTY_STRING,
                description = it.description ?: Constants.EMPTY_STRING,
                imageUrl = it.images?.firstOrNull()?.image ?: Constants.EMPTY_STRING,
                category = it.categories?.firstOrNull() ?: Constants.EMPTY_STRING,
                city = it.location?.name ?: Constants.EMPTY_STRING,
                isFree = it.isFree == true,
                dates = formatDates(it.dates),
                address = it.place?.address ?: Constants.EMPTY_STRING,
                siteUrl = it.siteUrl,
                tags = it.tags ?: emptyList(),
                favoritesCount = it.favoritesCount ?: 0
            )
        } ?: SearchResultModel.EMPTY
    }

    private fun formatDates(dates: List<KudagoDateResponse>?): String {
        if (dates.isNullOrEmpty()) return ""

        val firstDate = dates.first()
        val start = firstDate.start
        val end = firstDate.end

        if (start == null) return ""

        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val startDate = Date(start * 1000)

        return if (end != null) {
            val endDate = Date(end * 1000)
            "${sdf.format(startDate)} - ${sdf.format(endDate)}"
        } else {
            sdf.format(startDate)
        }
    }
}
