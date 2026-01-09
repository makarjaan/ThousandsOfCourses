package makarova.citypulse.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import makarova.citypulse.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("UPDATE users SET name = :newName WHERE email = :email")
    suspend fun updateUserName(email: String, newName: String): Int

    @Query("UPDATE users SET avatar_url = :avatarUrl WHERE email = :email")
    suspend fun updateUserAvatar(email: String, avatarUrl: String?): Int

    @Query("UPDATE users SET name = :newName, avatar_url = :avatarUrl WHERE email = :email")
    suspend fun updateUserProfile(email: String, newName: String, avatarUrl: String?): Int

    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUserByEmail(email: String): Int

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUsersCount(): Int
}