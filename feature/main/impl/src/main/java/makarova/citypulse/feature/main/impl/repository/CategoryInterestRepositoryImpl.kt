package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.database.dao.CategoryInterestDao
import makarova.citypulse.feature.main.api.repository.CategoryInterestRepository
import javax.inject.Inject

class CategoryInterestRepositoryImpl @Inject constructor(
    private val dao: CategoryInterestDao
) : CategoryInterestRepository {

    override suspend fun getUserCategoryScores(email: String): Map<String, Int> {
        return dao.getAllForUser(email)
            .associate { it.category to it.score }
    }
}
