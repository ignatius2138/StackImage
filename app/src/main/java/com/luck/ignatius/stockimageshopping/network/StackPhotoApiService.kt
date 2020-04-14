package com.luck.ignatius.stockimageshopping.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.shutterstock.com/v2/"
private const val USER_NAME = "YqhShgqoNg9f0zcLU4dmTCSEA1hLPwzp"
private const val PASS = "mS8hHA7JGkM9oGAj"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val client: OkHttpClient =  OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor(USER_NAME, PASS))
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface StackPhotoApiService {
    @GET("images/search")
    fun getImages(): Deferred<Image>
}

object StackPhotoApi {
    val retrofitService: StackPhotoApiService by lazy {
        retrofit.create(StackPhotoApiService::class.java)
    }
}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}