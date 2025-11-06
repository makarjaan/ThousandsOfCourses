package makarova.thousandsofcourses.feature.auth.impl.repository

import makarova.thousandsofcourses.database.dao.UserDao
import makarova.thousandsofcourses.database.mapper.DbMapper
import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val dbMapper: DbMapper
): AuthRepository {

    override suspend fun loginUser(userLoginModel: UserLoginModel) {
        val userEntity = dbMapper.mapToUserEntity(userLoginModel)
        userDao.saveUser(userEntity)
    }

    override suspend fun deleteUser() {
        userDao.deleteAllUser()
    }

}