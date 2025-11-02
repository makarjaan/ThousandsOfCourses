package makarova.thousandsofcourses.feature.auth.impl.repository

import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

): AuthRepository {

    override suspend fun loginUser(login: String, password: String) {
        TODO("Not yet implemented")
    }
}