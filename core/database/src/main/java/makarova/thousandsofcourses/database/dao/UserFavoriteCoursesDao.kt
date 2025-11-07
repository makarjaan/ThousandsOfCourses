package makarova.thousandsofcourses.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import makarova.thousandsofcourses.database.entities.UserFavoriteCoursesEntity

@Dao
interface UserFavoriteCoursesDao {
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(userFavorite: UserFavoriteCoursesEntity)
    
    @Delete
    suspend fun removeFromFavorites(userFavorite: UserFavoriteCoursesEntity)

    @Query("DELETE FROM user_favorites_courses WHERE user_id = :userId AND course_id = :courseId")
    suspend fun removeFromFavorites(userId: Long, courseId: Long)

    @Query("SELECT COUNT(*) FROM user_favorites_courses" +
            " WHERE user_id = :userId AND course_id = :courseId")
    suspend fun isCourseFavorite(userId: Long, courseId: Long): Boolean

    @Query("SELECT course_id FROM user_favorites_courses WHERE user_id = :userId")
    suspend fun getFavoriteCoursesByUserId(userId: Long): List<Long>
}