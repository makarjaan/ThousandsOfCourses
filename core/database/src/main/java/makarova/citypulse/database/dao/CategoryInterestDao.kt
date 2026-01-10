package makarova.citypulse.database.dao

import androidx.room.*
import makarova.citypulse.database.entities.CategoryInterestEntity

@Dao
interface CategoryInterestDao {

    @Query("SELECT * FROM category_interest WHERE email = :email")
    suspend fun getAllForUser(email: String): List<CategoryInterestEntity>

    @Query("SELECT * FROM category_interest WHERE email = :email AND category = :category LIMIT 1")
    suspend fun getByCategory(email: String, category: String): CategoryInterestEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CategoryInterestEntity)

    @Update
    suspend fun update(entity: CategoryInterestEntity)

    @Transaction
    suspend fun incrementScore(email: String, category: String, delta: Int = 1) {
        val existing = getByCategory(email, category)
        if (existing != null) {
            insert(existing.copy(score = existing.score + delta))
        } else {
            insert(CategoryInterestEntity(email, category, delta))
        }
    }

    @Query("DELETE FROM category_interest WHERE email = :email")
    suspend fun clearUserPreferences(email: String)

    @Query("""
        SELECT category 
        FROM category_interest 
        WHERE email = :email 
        ORDER BY score DESC 
        LIMIT 3
    """)
    suspend fun getTopCategoriesForUser(email: String): List<String>
}
