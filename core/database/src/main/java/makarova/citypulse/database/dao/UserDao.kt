package makarova.citypulse.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import makarova.citypulse.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUsersCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUser()

    @Query("SELECT id FROM users LIMIT 1")
    suspend fun getCurrentUserId(): Long?
}