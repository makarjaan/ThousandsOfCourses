package makarova.thousandsofcourses.api.model

import makarova.thousandsofcourses.utils.Constants

data class CourseModel (
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) {
    companion object {
        val EMPTY = CourseModel(
            id = 0,
            title = Constants.EMPTY_STRING,
            text = Constants.EMPTY_STRING,
            price = Constants.EMPTY_STRING,
            rate = Constants.EMPTY_DOUBLE,
            startDate = Constants.EMPTY_STRING,
            hasLike = false,
            publishDate = Constants.EMPTY_STRING
        )
    }
}