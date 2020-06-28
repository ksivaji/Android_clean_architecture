package com.example.githubrepos.di

import android.app.Application
import androidx.room.Room
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.data.AppDatabase
import com.example.githubrepos.data.GithubRemoteDataSource
import com.example.githubrepos.data.local.GithubUserDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, GithubService::class.java)


    @Singleton
    @Provides
    fun provideDb(app: Application) =
        Room.databaseBuilder(app, AppDatabase::class.java, "GithubUser.db").build()


    @Singleton
    @Provides
    fun provideGithubUserDao(db: AppDatabase) = db.githubUserDao()

    @Singleton
    @Provides
    fun providesGithubRemoteDataSource(service: GithubService, dao: GithubUserDao) = GithubRemoteDataSource(service,dao)


    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
