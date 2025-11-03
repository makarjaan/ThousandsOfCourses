package makarova.thousandsofcourses.feature.auth.api.repository

import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel

interface AuthRepository {

    suspend fun loginUser(userLoginModel: UserLoginModel)

    suspend fun userInDb(): Boolean

}