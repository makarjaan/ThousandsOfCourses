package makarova.thousandsofcourses.database

import androidx.room.Database
import androidx.room.RoomDatabase
import makarova.thousandsofcourses.database.dao.UserDao
import makarova.thousandsofcourses.database.entities.UserEntity


@Database(
    entities = [
        UserEntity::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DB_LOG_KEY = "AppDb"
    }
}