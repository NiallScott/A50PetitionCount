package uk.org.rivernile.android.a50petitiontracker.dagger.main

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.org.rivernile.android.a50petitiontracker.repositories.petition.PetitionService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class PetitionRepositoryModule {

    @Provides
    fun providePetitionService(retrofit: Retrofit): PetitionService = retrofit.create(PetitionService::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder()
        .baseUrl("https://petition.parliament.uk")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
}