package makarova.citypulse.feature.auth.api.repository

import makarova.citypulse.feature.auth.api.model.UserLoginModel

interface AuthRepository {

    suspend fun loginUser(userLoginModel: UserLoginModel)

    suspend fun deleteUser()

}