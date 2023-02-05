package com.example.home.di

import android.content.Context
import com.example.home.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule{

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return localHttpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                val lang="en"
                val newRequest = builder.build()

                return@addInterceptor chain.proceed(newRequest)
            }
            .build()

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .run {
                baseUrl("https://77ab-196-221-102-249.eu.ngrok.io/")
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ) = context


}