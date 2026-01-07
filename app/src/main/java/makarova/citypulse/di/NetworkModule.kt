package makarova.citypulse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.network.BuildConfig.KUDA_GO_API_BASE_URL
import makarova.citypulse.network.KudagoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideKudaGoApi(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): KudagoApi {

        val retrofit = Retrofit.Builder()
            .baseUrl(KUDA_GO_API_BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)

        return retrofit.build().create(KudagoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        builder
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}