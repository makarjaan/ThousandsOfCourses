package makarova.citypulse.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "favorite_events",
    primaryKeys = ["event_id", "user_email"]
)
data class FavoriteEventEntity(

    @ColumnInfo(name = "event_id")
    val eventId: String,

    @ColumnInfo(name = "user_email", index = true)
    val userEmail: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "place")
    val place: String?,

    @ColumnInfo(name = "date_start")
    val dateStart: Long,

    @ColumnInfo(name = "date_end")
    val dateEnd: Long?,

    @ColumnInfo(name = "is_free")
    val isFree: Boolean,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "added_at")
    val addedAt: Long
)
