package makarova.citypulse.feature.main.api.repository

interface CategoryInterestRepository {
    suspend fun getUserCategoryScores(email: String): Map<String, Int>
}
