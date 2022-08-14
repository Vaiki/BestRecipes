package com.example.bestrecipes.data

import com.example.bestrecipes.api.RecipeAPI
import com.example.bestrecipes.utils.Constants

class RecipeRepository (private val recipeDao: RecipeDao){

    //retrofit
    suspend fun getResponseRecipes(query: String)= RecipeAPI.create().searchRecipes(Constants.TYPE, query, Constants.API_ID, Constants.API_KEY)
    //room
    val savedRecipes = recipeDao.getAllRecipes()
    suspend fun upsert(recipeData: RecipeData) = recipeDao.upsert(recipeData)
    suspend fun delete(recipeData: RecipeData) = recipeDao.deleteRecipe(recipeData)

}