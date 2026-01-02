package makarova.citypulse.database

import androidx.room.Database
import androidx.room.RoomDatabase
import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.entities.UserEntity


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