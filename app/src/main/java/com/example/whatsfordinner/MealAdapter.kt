package com.example.whatsfordinner

// Gerekli Android bileşenleri import edilir
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsfordinner.databinding.ItemMealBinding

// RecyclerView adapter'ı: liste verisini (meals) UI üzerinde göstermek için kullanılır
class MealAdapter(
    private var meals: List<MealModel>,              // Gösterilecek yemek listesi
    private val onDeleteClick: (MealModel) -> Unit   // Silme butonuna tıklandığında çalışacak callback
) : RecyclerView.Adapter<MealAdapter.MealVH>() {

    // ViewHolder: her bir satır görünümünü temsil eder (item_meal.xml)
    inner class MealVH(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Yeni ViewHolder oluşturulduğunda çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealVH {
        // item_meal.xml dosyasını inflate eder (her satırın layout’unu oluşturur)
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealVH(binding)
    }

    // Her öğe ekrana geldiğinde verileri bağlamak için çağrılır
    override fun onBindViewHolder(holder: MealVH, position: Int) {
        val meal = meals[position]                       // Listedeki mevcut öğe alınır
        holder.binding.mealName.text = meal.mealName     // TextView'e yemek ismi atanır

        // Silme butonuna tıklanınca dışarıdan gelen callback çalıştırılır
        holder.binding.deleteMeal.setOnClickListener {
            onDeleteClick(meal)
        }
    }

    // Toplam öğe sayısını döndürür (RecyclerView kaç satır göstereceğini buradan bilir)
    override fun getItemCount(): Int = meals.size

    // Liste güncellendiğinde yeni verileri alır ve ekrana yeniler
    fun updateList(newMeals: List<MealModel>) {
        meals = newMeals
        notifyDataSetChanged() // Tüm listeyi yeniler (basit ama verimli olmayan yöntem)
    }
}
