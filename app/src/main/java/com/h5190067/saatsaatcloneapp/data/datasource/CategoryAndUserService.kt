package com.h5190067.saatsaatcloneapp.data.datasource

import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoryAndUserService {
    @GET("saatsaatKullaniciJson.json")
    suspend fun getUsers(): Response<UserResponse>
    @GET("saatsaatjson.json")
    suspend fun getCategories(): Response<CategoriesAndProductsResponse>

    companion object {

        fun build(): CategoryAndUserService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(CategoryAndUserService::class.java)
        }
    }

}