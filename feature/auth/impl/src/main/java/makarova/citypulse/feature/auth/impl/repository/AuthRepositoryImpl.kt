package makarova.citypulse.feature.auth.impl.repository

import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.local.JwtManager
import makarova.citypulse.database.local.TokenStorage
import makarova.citypulse.database.mapper.UserMapper
import makarova.citypulse.feature.auth.api.model.UserModel
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val tokenStorage: TokenStorage,
    private val userMapper: UserMapper,
    private val jwtManager: JwtManager
) : AuthRepository {

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Boolean {
        if (userDao.getUserByEmail(email) != null) return false

        val userModel = UserModel(
            login = email,
            name = name,
            password = password
        )

        val userEntity = userMapper.mapToEntity(userModel)
        userDao.insertUser(userEntity)

        val token = jwtManager.generateToken(email)
        tokenStorage.saveToken(token)

        return true
    }

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {
        val userEntity = userDao.getUserByEmail(email) ?: return false

        userMapper.mapToModelWithPassword(userEntity, password) ?: return false

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

    override suspend fun getCurrentUser(): UserModel? {
        val token = tokenStorage.getToken() ?: return null
        val email = jwtManager.getEmail(token) ?: return null

        val userEntity = userDao.getUserByEmail(email) ?: return null
        return userMapper.mapToModel(userEntity)
    }

    override suspend fun updateUserProfile(
        email: String,
        newName: String?,
        avatarUrl: String?
    ): Boolean {
        return try {
            val user = userDao.getUserByEmail(email) ?: return false

            val updatedEntity = userMapper.updateEntity(
                entity = user,
                newName = newName,
                newAvatarUrl = avatarUrl
            )

            val rowsAffected = userDao.updateUser(updatedEntity)
            rowsAffected > 0
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun isLoggedIn(): Boolean {
        val token = tokenStorage.getToken() ?: return false
        return jwtManager.isTokenValid(token)
    }

    override suspend fun deleteAccount(email: String): Boolean {
        return try {
            userDao.deleteUserByEmail(email) > 0
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateUserName(
        email: String,
        newName: String
    ): Boolean {
        return try {
            val rowsAffected = userDao.updateUserName(email, newName)
            rowsAffected > 0
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun changePassword(email: String, oldPassword: String, newPassword: String): Boolean {
        return try {
            val user = userDao.getUserByEmail(email) ?: return false

            if (userMapper.hashPassword(oldPassword) != user.passwordHash) {
                return false
            }

            val newHash = userMapper.hashPassword(newPassword)
            val updatedEntity = user.copy(
                passwordHash = newHash,
                updatedAt = System.currentTimeMillis()
            )

            val rowsAffected = userDao.updateUser(updatedEntity)
            rowsAffected > 0
        } catch (e: Exception) {
            false
        }
    }
}

