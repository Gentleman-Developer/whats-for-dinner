package com.example.whatsfordinner

// Android ve Jetpack kütüphanelerinin importları
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsfordinner.databinding.FragmentListBinding

// Bu fragment, yemek listesini göstermekten sorumlu ekran (örneğin “ListFragment”)
class ListFragment : BaseFragment(R.layout.fragment_list) {

    // View Binding nesnesi (fragmentin layout’una erişim için)
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!! // null olmasını önlemek için “!!” kullanılıyor (dikkatli kullanılmalı)

    // ViewModel (veri yönetimi ve UI mantığı buradan geliyor)
    private lateinit var viewModel: MealViewModel

    // RecyclerView için adapter (listeyi ekrana bağlar)
    private lateinit var adapter: MealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment ilk oluşturulduğunda çağrılır
        // Burada henüz view binding veya UI erişimi yoktur
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment’in XML dosyasını inflate eder (FragmentListBinding kullanarak)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Fragment görünümü oluşturulduktan sonra çağrılır — artık UI erişimi var

        setHasOptionsMenu(true)
        // Fragment’in kendi menü öğelerini gösterebilmesini sağlar (Toolbar ile etkileşim için)

        // Repository ve ViewModel kurulumu
        val repository  = MealRepository(MealDatabase.getDatabase(requireContext()).mealDao())
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MealViewModel(repository) as T
            }
        }).get(MealViewModel::class.java)

        // RecyclerView adapter kurulumu — MealAdapter ile liste verisi bağlanıyor
        adapter = MealAdapter(emptyList()) { meal ->
            // Her öğedeki “sil” butonuna basıldığında çağrılacak callback
            viewModel.removeMeal(meal)
        }

        // RecyclerView’a adapter ve layout manager atama
        binding.listRecyclerview.adapter = adapter
        binding.listRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        // Flow (StateFlow) gözlemi — ViewModel’deki yemek listesi değişince UI güncellenir
        lifecycleScope.launchWhenStarted {
            viewModel.meals.collect { meals ->
                adapter.updateList(meals)
            }
        }

        // “Ekle” butonuna tıklama — yeni yemek ekleniyor
        binding.addButton.setOnClickListener {
            val mealName = binding.addEditText.text.toString()
            if (mealName.isNotEmpty()) {
                // Yeni bir yemek modeli oluştur
                val meal = MealModel(0, mealName)
                // ID 0, çünkü Room auto-generate ID atayacak (veritabanında @PrimaryKey(autoGenerate = true))
                viewModel.addMeal(meal)
                binding.addEditText.text!!.clear() // giriş kutusunu temizle
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hafıza sızıntısı (memory leak) önlemek için binding sıfırlanıyor
        _binding = null
    }
}
