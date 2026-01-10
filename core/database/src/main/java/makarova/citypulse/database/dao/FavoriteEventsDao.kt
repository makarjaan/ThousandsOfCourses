package makarova.citypulse.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import makarova.citypulse.database.entities.FavoriteEventEntity

@Dao
interface FavoriteEventsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: FavoriteEventEntity): Long

    @Delete
    suspend fun delete(event: FavoriteEventEntity)

    @Query("DELETE FROM favorite_events WHERE event_id = :eventId AND user_email = :userEmail")
    suspend fun deleteById(eventId: String, userEmail: String): Int

    @Query("SELECT * FROM favorite_events WHERE user_email = :userEmail ORDER BY added_at DESC")
    fun getAllByUser(userEmail: String): List<FavoriteEventEntity>

    @Query("SELECT * FROM favorite_events WHERE event_id = :eventId AND user_email = :userEmail")
    suspend fun getById(eventId: String, userEmail: String): FavoriteEventEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_events WHERE event_id = :eventId AND user_email = :userEmail)")
    suspend fun isFavorite(eventId: String, userEmail: String): Boolean

    @Query("SELECT COUNT(*) FROM favorite_events WHERE user_email = :userEmail")
    suspend fun getCount(userEmail: String): Int

    @Query("DELETE FROM favorite_events WHERE user_email = :userEmail")
    suspend fun clearAllByUser(userEmail: String)

    @Query("DELETE FROM favorite_events WHERE user_email = :userEmail AND event_id = :eventId")
    suspend fun removeFromFavorites(eventId: String, userEmail: String)
}