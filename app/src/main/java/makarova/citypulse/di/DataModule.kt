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
import makarova.citypulse.database.dao.UserDao
import makarova.citypulse.database.migrations.Migration_1_2
import makarova.citypulse.network.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import makarova.citypulse.network.BuildConfig.API_BASE_URL
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

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
    fun provideApi(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)

        return retrofit.build().create(Api::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

}