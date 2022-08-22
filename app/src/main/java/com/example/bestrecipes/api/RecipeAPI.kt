package com.example.bestrecipes.api

import com.example.bestrecipes.api.models.Hit
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.api.models.Recipes
import com.example.bestrecipes.utils.Constants
import com.example.bestrecipes.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {

    @GET("/api/recipes/v2")
    suspend fun searchRecipes(
        @Query("type") type: String,
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
    ): Response<Recipes>


    companion object {
        fun create(): RecipeAPI {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RecipeAPI::class.java)
        }
    }
}