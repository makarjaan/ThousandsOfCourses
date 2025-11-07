package makarova.thousandsofcourses.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "courses")
data class CourseEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "rate")
    val rate: Double,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "publishDate")
    val publishDate: String
)