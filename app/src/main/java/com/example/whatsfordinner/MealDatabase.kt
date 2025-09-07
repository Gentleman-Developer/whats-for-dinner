package com.example.whatsfordinner

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
@Database(entities = [MealModel::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDAO

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                ).build()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
