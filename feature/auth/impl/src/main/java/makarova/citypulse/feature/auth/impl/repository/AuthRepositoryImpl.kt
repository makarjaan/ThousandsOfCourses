package makarova.citypulse.feature.auth.impl.repository

import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.entities.UserEntity
import makarova.citypulse.database.local.JwtManager
import makarova.citypulse.database.local.TokenStorage
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import java.security.MessageDigest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val tokenStorage: TokenStorage,
    private val jwtManager: JwtManager
) : AuthRepository {

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Boolean {
        if (userDao.getUserByEmail(email) != null) return false

        val hash = hashPassword(password)
        userDao.insertUser(UserEntity(email, name, hash))
        return true
    }

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {
        val user = userDao.getUserByEmail(email) ?: return false

        if (user.passwordHash != hashPassword(password)) return false

        val token = jwtManager.generateToken(email)
        tokenStorage.saveToken(token)

        return true
    }

    override suspend fun logout() {
        tokenStorage.clearToken()
    }

    override suspend fun getToken(): String? =
        tokenStorage.getToken()

    override suspend fun refreshToken(): String? {
        val token = tokenStorage.getToken() ?: return null

        return if (jwtManager.isTokenValid(token)) {
            token
        } else {
            val email = jwtManager.getEmail(token) ?: return null
            val newToken = jwtManager.generateToken(email)
            tokenStorage.saveToken(newToken)
            newToken
        }
    }

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())

        return bytes.joinToString("") { "%02x".format(it) }
    }
}

