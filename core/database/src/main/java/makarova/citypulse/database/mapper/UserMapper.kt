package makarova.citypulse.database.mapper

import makarova.citypulse.database.entities.UserEntity
import makarova.citypulse.feature.auth.api.model.UserModel
import java.security.MessageDigest
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun mapToEntity(user: UserModel): UserEntity {
        return UserEntity(
            email = user.login,
            name = user.name,
            passwordHash = hashPassword(user.password),
            avatarUrl = user.avatarUrl
        )
    }

    fun mapToModel(entity: UserEntity): UserModel {
        return UserModel(
            login = entity.email,
            name = entity.name,
            password = "",
            avatarUrl = entity.avatarUrl
        )
    }

    fun mapToModelWithPassword(entity: UserEntity, inputPassword: String): UserModel? {
        return if (hashPassword(inputPassword) == entity.passwordHash) {
            UserModel(
                login = entity.email,
                name = entity.name,
                password = "",
                avatarUrl = entity.avatarUrl
            )
        } else {
            null
        }
    }

    fun updateEntity(
        entity: UserEntity,
        newName: String? = null,
        newAvatarUrl: String? = null,
        newPassword: String? = null
    ): UserEntity {
        return entity.copy(
            name = newName ?: entity.name,
            avatarUrl = newAvatarUrl ?: entity.avatarUrl,
            passwordHash = newPassword?.let { hashPassword(it) } ?: entity.passwordHash,
            updatedAt = System.currentTimeMillis()
        )
    }


    fun hashPassword(password: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

}