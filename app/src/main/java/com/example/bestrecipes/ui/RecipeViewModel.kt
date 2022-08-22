package com.example.bestrecipes.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.api.models.Recipes
import com.example.bestrecipes.data.RecipeArticle
import com.example.bestrecipes.data.RecipeRepository
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class RecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    val getRecipesLiveData = MutableLiveData<Recipes>()
    val recipeForSaveLiveData = MutableLiveData<RecipeArticle>()
    val recipeSavedLiveData = MutableLiveData<RecipeArticle>()


    fun setRecipeArticle(rInet: Recipe) {
        recipeForSaveLiveData.value = RecipeArticle(
            0,
            rInet.label,
            rInet.calories.toInt(),
            rInet.totalDaily.ENERC_KCAL.quantity.toInt() / rInet.yield,
            rInet.totalNutrients.PROCNT.quantity.toInt() / rInet.yield,
            rInet.totalNutrients.FAT.quantity.toInt() / rInet.yield,
            rInet.totalNutrients.CHOCDF.quantity.toInt() / rInet.yield,
            ingredients(rInet.ingredientLines),
            rInet.yield,
            rInet.image,
            rInet.url
        )
    }

    val ingredients = { list: List<String> ->
        val builder = StringBuilder()
        for (i in list) {
            builder.append("- $i\n")
        }
        builder.toString()
    }

    fun getResponseRecipes(query: String) {
        viewModelScope.launch {
            val response = recipeRepository.getResponseRecipes(query)
            if (response.isSuccessful) getRecipesLiveData.postValue(response.body())
        }
    }

    fun saveRecipe(recipe: RecipeArticle) {
        viewModelScope.launch {
            recipeRepository.upsert(recipe)
        }
    }

    fun delRecipe(recipe: RecipeArticle) = viewModelScope.launch { recipeRepository.delete(recipe) }

    fun getSavedRecipe() = recipeRepository.savedRecipes


}
