package makarova.thousandsofcourses.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_favorites_courses",
    primaryKeys = ["user_id", "course_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["course_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["user_id", "course_id"], unique = true),
        Index(value = ["course_id"])
    ]
)
data class UserFavoriteCoursesEntity (

    @ColumnInfo(name = "user_id")
    val userId: Long,

    @ColumnInfo(name = "course_id")
    val courseId: Long,

    @ColumnInfo(name = "added_date")
    val addedDate: Long = System.currentTimeMillis()
)