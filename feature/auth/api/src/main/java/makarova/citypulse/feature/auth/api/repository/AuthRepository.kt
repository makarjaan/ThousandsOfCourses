package makarova.citypulse.feature.auth.api.repository

import makarova.citypulse.feature.auth.api.model.UserModel

interface AuthRepository {

    suspend fun register(username: String, name: String, password: String): Boolean

    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()

    suspend fun getToken(): String?

    suspend fun refreshToken(): String?

    suspend fun getCurrentUser(): UserModel?

    suspend fun updateUserProfile(email: String, newName: String? = null, newPassword: String? = null): Boolean

    suspend fun isLoggedIn(): Boolean

    suspend fun deleteAccount(email: String): Boolean

    suspend fun updateUserName(email: String, newName: String): Boolean

    suspend fun changePassword(email: String, oldPassword: String, newPassword: String): Boolean
}
