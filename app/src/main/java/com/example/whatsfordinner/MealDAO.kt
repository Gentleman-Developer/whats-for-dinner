package com.example.whatsfordinner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDAO {

    @Query("SELECT * FROM meals")
    fun getAll(): Flow<List<MealModel>>   // UI otomatik güncellenir

    @Insert
    suspend fun insertMeal(mealModel: MealModel)

    @Delete
    suspend fun deleteMeal(mealModel: MealModel)
}
