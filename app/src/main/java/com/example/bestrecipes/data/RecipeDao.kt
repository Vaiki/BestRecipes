package com.example.bestrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe:RecipeArticle): Long

    @Query("SELECT * FROM RECIPE_TABLE")
    fun getAllRecipes(): LiveData<List<RecipeArticle>>

    @Delete
    suspend fun deleteRecipe(recipe:RecipeArticle)

}