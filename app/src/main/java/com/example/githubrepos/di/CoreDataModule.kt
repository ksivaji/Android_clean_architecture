package com.example.githubrepos.di

import com.example.githubrepos.BuildConfig
import com.example.githubrepos.data.GithubRepo
import com.example.githubrepos.utils.UserRepoDeserializer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.reflect.TypeToken



/**
 * Dagger module to provide core data functionality.
 */
@Module
class CoreDataModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val assetsType = object : TypeToken<List<GithubRepo>>() {}.type
        return GsonBuilder().registerTypeAdapter(assetsType, UserRepoDeserializer()).create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)
}
