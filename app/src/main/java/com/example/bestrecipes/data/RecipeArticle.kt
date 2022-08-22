package com.example.bestrecipes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="RECIPE_TABLE")
data class RecipeArticle(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val label:String,
    val calories:Int,
    @ColumnInfo(name = "daily_value")
    val dailyValue:Int,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val ingredients:String,
    val serving:Int,
    val image:String,
    val url:String
)