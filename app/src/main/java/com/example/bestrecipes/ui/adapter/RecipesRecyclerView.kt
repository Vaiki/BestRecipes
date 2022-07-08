package com.example.bestrecipes.ui.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestrecipes.R
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.api.models.Recipes
import com.example.bestrecipes.databinding.ItemRecipeBinding
import java.lang.StringBuilder

class RecipesRecyclerView(private val recipes: Recipes) :
    RecyclerView.Adapter<RecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes, position)
       holder.itemView.setOnClickListener {
           onItemClickListener?.let { it(recipes.hits[position].recipe) }
       }
    }

    override fun getItemCount(): Int = recipes.hits.size

    private var onItemClickListener: ((Recipe) -> Unit)? = null
    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }
}


class RecipesViewHolder(private val binding: ItemRecipeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(recipes: Recipes, pos: Int) {
        val calories: Int =
            recipes.hits[pos].recipe.calories.toInt() / recipes.hits[pos].recipe.yield
        val dailyValue: Int =
            recipes.hits[pos].recipe.totalDaily.ENERC_KCAL.quantity.toInt() / recipes.hits[pos].recipe.yield
        val protein: Int =
            recipes.hits[pos].recipe.totalNutrients.PROCNT.quantity.toInt() / recipes.hits[pos].recipe.yield
        val fat: Int =
            recipes.hits[pos].recipe.totalNutrients.FAT.quantity.toInt() / recipes.hits[pos].recipe.yield
        val carbs: Int =
            recipes.hits[pos].recipe.totalNutrients.CHOCDF.quantity.toInt() / recipes.hits[pos].recipe.yield

        val ingredients = { list: List<String> ->
            val builder = StringBuilder()
            for (i in list) {
                builder.append("- $i\n")
            }
            builder.toString()
        }

        with(binding) {
            tvLabelRecipe.text = recipes.hits[pos].recipe.label
            tvCaloriesServing.text = "Calories/serving: $calories kcal"
            tvDailyValue.text = "Daily value: $dailyValue %"
            tvProtein.text = "Protein: $protein g"
            tvFat.text = "Fat: $fat g"
            tvCarbs.text = "Carbs: $carbs g"
            tvServing.text = "Ingredients for ${recipes.hits[pos].recipe.yield} servings:"
            tvIngredients.text = ingredients(recipes.hits[pos].recipe.ingredientLines)
            Glide.with(itemView.context)
                .load(recipes.hits[pos].recipe.image)
                .centerCrop()
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_dining_24)
                .into(ivRecipe)

        }
    }


}