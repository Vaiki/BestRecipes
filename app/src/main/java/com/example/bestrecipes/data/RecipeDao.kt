package com.example.bestrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe:RecipeData): Long

    @Query("SELECT * FROM RECIPE_TABLE")
    fun getAllRecipes(): LiveData<List<RecipeData>>

    @Delete
    suspend fun deleteRecipe(recipe:RecipeData)

}