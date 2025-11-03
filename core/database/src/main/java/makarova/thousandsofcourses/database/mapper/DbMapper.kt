package makarova.thousandsofcourses.database.mapper

import makarova.thousandsofcourses.database.entities.UserEntity
import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel
import javax.inject.Inject

class DbMapper @Inject constructor() {

    fun mapToUserModel(userEntity: UserEntity): UserLoginModel {
        return UserLoginModel(
            login = userEntity.email,
            password = userEntity.password
        )
    }

    fun mapToUserEntity(userModel: UserLoginModel): UserEntity {
        return UserEntity(
            email = userModel.login,
            password = userModel.password
        )
    }
}