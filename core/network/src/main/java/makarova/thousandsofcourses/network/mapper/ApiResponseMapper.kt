package makarova.thousandsofcourses.network.mapper

import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.network.response.CoursesResponse
import makarova.thousandsofcourses.network.response.CoursesResponseItem
import makarova.thousandsofcourses.utils.Constants
import javax.inject.Inject

class ApiResponseMapper @Inject constructor() {

    fun mapToCourse(input: CoursesResponseItem?): CourseModel {
        return input?.let {
            CourseModel(
                id = it.id ?: Constants.EMPTY_STRING,
                title = it.title ?: Constants.EMPTY_STRING,
                text = it.text ?: Constants.EMPTY_STRING,
                price = it.price ?: Constants.EMPTY_STRING,
                rate = it.rate ?: Constants.EMPTY_DOUBLE,
                startDate = it.startDate ?: Constants.EMPTY_STRING,
                hasLike = it.hasLike ?: false,
                publishDate = it.publishDate ?: Constants.EMPTY_STRING
            )
        } ?: CourseModel.EMPTY
    }

    fun mapToListCourses(input: CoursesResponse?): List<CourseModel> {
        return input?.courses?.map { course -> mapToCourse(course) } ?: emptyList()
    }
}