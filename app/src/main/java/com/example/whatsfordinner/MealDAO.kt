package com.example.whatsfordinner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Bu interface, Room veritabanı işlemlerini tanımlar.
// Room, buradaki methodlara göre otomatik SQL kodu üretir.
@Dao
interface MealDAO {

    // 🔹 Tüm yemekleri veritabanından çeker.
    // Flow döndürmesi sayesinde liste değiştiğinde (ekleme/silme) otomatik olarak güncellenir.
    @Query("SELECT * FROM meals")
    fun getAll(): Flow<List<MealModel>>

    // 🔹 Yeni bir yemek ekler.
    // suspend: coroutine içinde çağrılmak zorunda (UI thread’de çalışmaz).
    @Insert
    suspend fun insertMeal(mealModel: MealModel)

    // 🔹 Verilen yemek nesnesini siler.
    @Delete
    suspend fun deleteMeal(mealModel: MealModel)
}
