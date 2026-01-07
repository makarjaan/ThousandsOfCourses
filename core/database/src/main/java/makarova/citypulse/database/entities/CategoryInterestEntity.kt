package makarova.citypulse.feature.main.impl.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "category_interest",
    primaryKeys = ["email", "category"]
)
data class CategoryInterestEntity(
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "score")
    val score: Int
)