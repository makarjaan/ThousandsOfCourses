package makarova.thousandsofcourses.feature.auth.impl.repository

import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

): AuthRepository {

    override suspend fun loginUser(userLoginModel: UserLoginModel) {
        TODO("Not yet implemented")
    }
}