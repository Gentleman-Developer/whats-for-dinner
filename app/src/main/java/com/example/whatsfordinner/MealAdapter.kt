package com.example.whatsfordinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsfordinner.databinding.ItemMealBinding
class MealAdapter(
    private var meals: List<MealModel>,
    private val onDeleteClick: (MealModel) -> Unit
) : RecyclerView.Adapter<MealAdapter.MealVH>() {

    inner class MealVH(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealVH {
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealVH(binding)
    }

    override fun onBindViewHolder(holder: MealVH, position: Int) {
        val meal = meals[position]
        holder.binding.mealName.text = meal.mealName
        holder.binding.deleteMeal.setOnClickListener {
            onDeleteClick(meal)
        }
    }

    override fun getItemCount(): Int = meals.size

    fun updateList(newMeals: List<MealModel>) {
        meals = newMeals
        notifyDataSetChanged()
    }
}
