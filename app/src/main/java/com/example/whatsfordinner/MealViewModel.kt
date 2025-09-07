package com.example.whatsfordinner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MealViewModel(private val repository: MealRepository) : ViewModel() {

    val meals: StateFlow<List<MealModel>> = repository.getAllMeals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addMeal(meal: MealModel) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    fun removeMeal(meal: MealModel) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }
}
