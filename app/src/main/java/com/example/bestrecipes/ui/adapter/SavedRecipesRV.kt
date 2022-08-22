package com.example.bestrecipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestrecipes.R
import com.example.bestrecipes.data.RecipeArticle
import com.example.bestrecipes.databinding.ItemRecipeBinding

class SavedRecipesRV(private val recipe: List<RecipeArticle>) :
    RecyclerView.Adapter<SavedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(recipe[position])
    }

    override fun getItemCount(): Int = recipe.size
}

class SavedViewHolder(private val binding: ItemRecipeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: RecipeArticle) {
        with(binding) {
            tvCarbs.text = recipe.carbs.toString()
            tvFat.text = recipe.fat.toString()
            tvIngredients.text = recipe.ingredients
            tvProtein.text = recipe.protein.toString()
            tvServing.text = recipe.serving.toString()
            tvCaloriesServing.text = (recipe.calories / recipe.serving).toString()
            tvDailyValue.text = recipe.dailyValue.toString()
            tvLabelRecipe.text = recipe.label
            Glide.with(itemView.context)
                .load(recipe.image)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_dining_24)
                .into(ivRecipe)

        }
    }
}