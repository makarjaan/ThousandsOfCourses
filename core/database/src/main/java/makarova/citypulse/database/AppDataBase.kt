package makarova.citypulse.database

import androidx.room.Database
import androidx.room.RoomDatabase
import makarova.citypulse.database.dao.CategoryInterestDao
import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.entities.UserEntity
import makarova.citypulse.database.entities.CategoryInterestEntity


@Database(
    entities = [
        UserEntity::class, CategoryInterestEntity::class],
    version = 2
)
abstract class AppDataBase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val categoryInterestDao: CategoryInterestDao

    companion object {
        const val DB_LOG_KEY = "AppDb"
    }
}