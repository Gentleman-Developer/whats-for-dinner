package com.example.whatsfordinner

// Repository katmanı, veriye erişim işlerini tek bir noktada toplar.
// Böylece ViewModel doğrudan DAO ile uğraşmak zorunda kalmaz.
class MealRepository(private val mealDAO: MealDAO) {

    // 🔹 Tüm yemekleri döndürür.
    // DAO'daki getAll() metodunu çağırır, Flow<List<MealModel>> döner.
    fun getAllMeals() = mealDAO.getAll()

    // 🔹 Bir yemeği siler.
    // 'suspend' olduğu için coroutine içinde çağrılmalıdır.
    suspend fun deleteMeal(meal: MealModel) = mealDAO.deleteMeal(meal)

    // 🔹 Yeni bir yemek ekler.
    // 'suspend' → bu da veritabanı işlemi olduğu için arka planda çalışmalı.
    suspend fun insertMeal(meal: MealModel) = mealDAO.insertMeal(meal)
}
