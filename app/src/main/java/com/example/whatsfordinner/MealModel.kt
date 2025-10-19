package com.example.whatsfordinner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 🔹 Room'a bu veri sınıfının bir tablo olduğunu söylüyoruz.
// 'tableName = "meals"' => tablo adı "meals" olacak.
@Entity(tableName = "meals")
data class MealModel(

    // 🔹 Her yemek kaydının benzersiz kimliği (id).
    // Room bu değeri otomatik olarak artırır (autoGenerate = true).
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,

    // 🔹 Tablodaki sütun adı 'meal_name' olacak.
    // Kotlin tarafında 'mealName' ile erişilir.
    @ColumnInfo(name = "meal_name") val mealName: String
)
