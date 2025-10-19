package com.example.whatsfordinner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.whatsfordinner.databinding.FragmentRandomBinding

// 🔹 RandomFragment: Kullanıcının listedeki yemeklerden rastgele birini görmesini sağlar.
class RandomFragment : BaseFragment(R.layout.fragment_random) {

    // 🔹 ViewBinding kullanımı. _binding nullable, binding safe call ile erişiliyor
    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    // 🔹 Fragment'te kullanılacak ViewModel
    private lateinit var viewModel: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 🔹 Bu fragment menü öğelerini kullanacak
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 🔹 Fragment layoutunu binding ile inflate et
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Repository oluştur: DAO üzerinden veritabanı ile bağlantı
        val repository = MealRepository(MealDatabase.getDatabase(requireContext()).mealDao())

        // 🔹 ViewModelProvider ile MealViewModel örneği oluştur
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MealViewModel(repository) as T
            }
        }).get(MealViewModel::class.java)

        // 🔹 ViewModel'deki meals StateFlow'unu collect ile gözlemle
        lifecycleScope.launchWhenStarted {
            viewModel.meals.collect { meals ->
                // 🔹 Random butonuna tıklandığında rastgele bir yemek seç
                binding.randomButton.setOnClickListener {
                    if (meals.isNotEmpty()) {
                        val randomMeal = meals.random() // Liste boş değilse rastgele seç
                        binding.randomText.text = randomMeal.mealName
                    } else {
                        binding.randomText.text = getString(R.string.list_is_empty) // Liste boşsa uyarı
                    }
                }
            }
        }

    }

    // 🔹 Toolbar menüsünü inflate et
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tool_up_bar, menu) // Menü XML’den + ve diğer ikonlar
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 🔹 Menü item tıklamalarını yakala
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addMeal -> {
                // 🔹 + ikonuna tıklayınca ListFragment'e git
                findNavController().navigate(R.id.action_randomFragment_to_listFragment)
                true
            }
            R.id.aboutApp -> {
                // 🔹 About ikonuna tıklayınca AboutFragment'e git
                findNavController().navigate(R.id.action_randomFragment_to_aboutFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 🔹 Fragment view destroy olduğunda binding referansını temizle
        _binding = null
    }
}
