package makarova.citypulse.feature.auth.api.repository

interface AuthRepository {

    suspend fun register(username: String, name: String, password: String): Boolean

    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()

    suspend fun getToken(): String?

    suspend fun refreshToken(): String?
}
