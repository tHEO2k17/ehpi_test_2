package co.effectstudios.resplash.core.di

import android.content.Context
import androidx.room.Room
import co.effectstudios.resplash.core.Constants.DB_NAME
import co.effectstudios.resplash.data.local.ResplashDatabase
import co.effectstudios.resplash.data.remote.API_VERSION
import co.effectstudios.resplash.data.remote.ApiService
import co.effectstudios.resplash.data.remote.BASE_URL
import co.effectstudios.resplash.data.remote.ConnectivityInterceptorImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, ResplashDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideAuthorDao(db: ResplashDatabase) = db.authorDao()

    @Singleton
    @Provides
    fun provideConnectivityInterceptor(@ApplicationContext context: Context) =
        ConnectivityInterceptorImpl(context)

    @Singleton
    @Provides
    fun provideOkHttp(
        connectivityInterceptorImpl: ConnectivityInterceptorImpl
    ) = OkHttpClient.Builder().addInterceptor(connectivityInterceptorImpl).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL + API_VERSION)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    // TODO : Injections for APi Service, Network data sources, repositories
}