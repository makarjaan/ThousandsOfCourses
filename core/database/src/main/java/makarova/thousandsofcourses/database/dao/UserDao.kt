package makarova.thousandsofcourses.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import makarova.thousandsofcourses.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUsersCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUser()
}