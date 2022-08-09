package com.example.bestrecipes.ui

import android.provider.SyncStateContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestrecipes.api.RecipeAPI
import com.example.bestrecipes.api.models.Hit
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.api.models.Recipes
import com.example.bestrecipes.utils.Constants
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    val getRecipesLiveData = MutableLiveData<Recipes>()
    fun getResponseRecipes(query: String) {
        viewModelScope.launch {
            val response = RecipeAPI.create()
                .searchRecipes(Constants.TYPE, query, Constants.API_ID, Constants.API_KEY)
            if (response.isSuccessful) getRecipesLiveData.postValue(response.body())
        }
    }
}