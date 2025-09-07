package com.example.whatsfordinner

class MealRepository(private val mealDAO: MealDAO) {

    fun  getAllMeals() = mealDAO.getAll()

   suspend fun  deleteMeal(meal: MealModel) = mealDAO.deleteMeal(meal)
   suspend fun  insertMeal(meal: MealModel) =mealDAO.insertMeal(meal)

}