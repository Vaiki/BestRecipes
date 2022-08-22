package com.example.bestrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeArticle::class], version = 1, exportSchema = false)
abstract class RecipeDB : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}