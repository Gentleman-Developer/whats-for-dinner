package com.example.whatsfordinner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// 🔹 ViewModel: UI (Fragment/Activity) ile veri kaynağı (Repository) arasında köprü görevi görür.
// Veriyi saklar, coroutine ile asenkron işlemleri yönetir.
class MealViewModel(private val repository: MealRepository) : ViewModel() {

    // 🔹 Flow verisini StateFlow'a dönüştürüyoruz.
    // Böylece UI (örneğin ListFragment), bu StateFlow'u observe ederek
    // her veri değiştiğinde otomatik güncellenir.
    val meals: StateFlow<List<MealModel>> = repository.getAllMeals()
        .stateIn(
            scope = viewModelScope, // ViewModel'e bağlı coroutine alanı (otomatik iptal edilir)
            started = SharingStarted.WhileSubscribed(5000), // UI aktifken veri yayını aktif olur
            initialValue = emptyList() // Başlangıç değeri (boş liste)
        )

    // 🔹 Yeni yemek ekleme işlemi.
    // suspend fonksiyon çağrıldığı için coroutine içinde çalıştırılır.
    fun addMeal(meal: MealModel) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    // 🔹 Yemek silme işlemi.
    fun removeMeal(meal: MealModel) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }
}
