package makarova.citypulse.network.mapper

import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.network.pojo.response.KudagoCategoryResponse
import makarova.citypulse.network.pojo.response.KudagoEventResponse
import makarova.citypulse.network.pojo.response.KudagoEventsResponse
import makarova.citypulse.utils.Constants
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


}
