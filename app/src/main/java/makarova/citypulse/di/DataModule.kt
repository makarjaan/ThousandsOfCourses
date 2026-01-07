package makarova.citypulse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import makarova.citypulse.database.AppDataBase
import makarova.citypulse.database.dao.CategoryInterestDao
import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.migrations.Migration_1_2

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) : AppDataBase = Room
        .databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_LOG_KEY)
        .addMigrations(Migration_1_2())
        .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDataBase) : UserDao = db.userDao

    @Provides
    @Singleton
    fun provideCategoryInterestDao(db: AppDataBase): CategoryInterestDao = db.categoryInterestDao
}