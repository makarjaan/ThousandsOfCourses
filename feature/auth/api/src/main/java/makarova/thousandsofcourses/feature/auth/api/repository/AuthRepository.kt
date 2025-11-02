package makarova.thousandsofcourses.feature.auth.api.repository

interface AuthRepository {

    suspend fun loginUser(login: String, password: String)

}