package com.example.retrofit.di

import com.example.retrofit.data.GlobalRepository
import com.example.retrofit.data.RepositoryImpl
import com.example.retrofit.data.WeatherRepository
import com.example.retrofit.data.retrofit.CloudModule
import com.example.retrofit.navigationUtils.AppNavigator
import com.example.retrofit.navigationUtils.AppNavigatorImpl
import com.example.retrofit.data.retrofit.MainApi
import com.example.retrofit.data.retrofit.WeatherApi
import com.example.retrofit.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun bindAppNavigator(): AppNavigator {
        return AppNavigatorImpl()
    }

    @Singleton
    @Provides
    fun provideGlobalRepository(mainApi: MainApi, weatherApi: WeatherApi): GlobalRepository {
        return RepositoryImpl(mainApi, weatherApi)
    }

    @Singleton
    @Provides
    fun provideRepositoryWeather(repository: GlobalRepository): WeatherRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideRepository(repository: GlobalRepository): Repository {
        return repository
    }

}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): MainApi {
        return CloudModule.Base().service(MainApi::class.java, "https://dummyjson.com")
    }

    @Provides
    @Singleton
    fun provideRetrofitWeather(): WeatherApi {
        return CloudModule.Base().service(WeatherApi::class.java, "https://api.weatherapi.com/v1/")
    }
}