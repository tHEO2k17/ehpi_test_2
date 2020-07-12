package co.effectstudios.resplash.data.remote

import co.effectstudios.resplash.data.remote.response.AuthorResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://picsum.photos/"
const val API_VERSION = "v2/"

//https://picsum.photos/v2/list
interface ApiService {

    @GET("list/")
    fun getAuthors(): Deferred<List<AuthorResponse>>


//
//    companion object {
//        operator fun invoke(
//            connectivityInterceptor: ConnectivityInterceptor
//        ): ApiService {
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(connectivityInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(BASE_URL + API_VERSION)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        }
//    }
}