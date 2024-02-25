package com.mayursarode.newsapp.di.module

import android.content.Context
import androidx.room.Room
import com.mayursarode.newsapp.data.api.ApiKeyInterceptor
import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.local.database.DatabaseService
import com.mayursarode.newsapp.data.local.database.NewsDatabase
import com.mayursarode.newsapp.data.local.database.NewsDatabaseService
import com.mayursarode.newsapp.di.BaseUrl
import com.mayursarode.newsapp.di.DatabaseName
import com.mayursarode.newsapp.di.NetworkApiKey
import com.mayursarode.newsapp.utils.Constants.API_KEY
import com.mayursarode.newsapp.utils.Constants.BASE_URL
import com.mayursarode.newsapp.utils.DefaultDispatcherProvider
import com.mayursarode.newsapp.utils.DefaultResourceProvider
import com.mayursarode.newsapp.utils.DispatcherProvider
import com.mayursarode.newsapp.utils.ResourceProvider
import com.mayursarode.newsapp.utils.logger.AppLogger
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.DefaultNetworkHelper
import com.mayursarode.newsapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)

    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideStringHelper(@ApplicationContext context: Context): ResourceProvider {
        return DefaultResourceProvider(context)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "news-database"

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(newsDatabase: NewsDatabase): DatabaseService {
        return NewsDatabaseService(newsDatabase)
    }

}