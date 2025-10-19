package com.example.whatsfordinner

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 🔹 Room’a hangi tabloyu (entity) ve hangi versiyonu kullanacağını söylüyoruz.
@Database(entities = [MealModel::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    // DAO bağlantısı: MealDAO veritabanıyla etkileşim kurmak için burada çağrılacak.
    abstract fun mealDao(): MealDAO

    companion object {
        // 🔹 INSTANCE değişkeni veritabanının tek bir örneğini saklar.
        // @Volatile: Thread’ler arasında tutarlılığı garanti eder.
        @Volatile
        private var INSTANCE: MealDatabase? = null

        // 🔹 Bu fonksiyon, veritabanının bir örneğini (singleton) döndürür.
        fun getDatabase(context: Context): MealDatabase {
            // Eğer veritabanı daha önce oluşturulmamışsa synchronized ile oluşturulur.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,       // Context (app context olmalı)
                    MealDatabase::class.java,         // Database sınıfı
                    context.getString(R.string.meal_database) // Veritabanı ismi
                )
                    // Veritabanı ilk defa oluşturulduğunda çağrılacak özel işlemler
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // 🔹 Veritabanı ilk kez oluşturulurken örnek yemekleri ekliyoruz.
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).mealDao().insertMeal(MealModel(mealName = "Pizza"))
                                getDatabase(context).mealDao().insertMeal(MealModel(mealName = "Burger"))
                                getDatabase(context).mealDao().insertMeal(MealModel(mealName = "Pasta"))
                            }
                        }
                    })
                    .build()
                // Oluşturulan örneği sakla
                INSTANCE = instance
                instance
            }
        }
    }
}
