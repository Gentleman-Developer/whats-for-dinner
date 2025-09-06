package com.example.whatsfordinner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.whatsfordinner.databinding.FragmentListBinding
import com.example.whatsfordinner.databinding.FragmentRandomBinding

class SuggestionFragment : Fragment() {

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment menüsü kullanacağımızı belirt
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Öner butonuna tıklandığında rastgele yemek seç
        binding.randomButton.setOnClickListener {
            val list = listOf("Pizza", "Pasta", "Salad")
            val randomFood = list.random()
            binding.randomText.text = randomFood
        }
    }

    // Toolbar menüsü oluştur
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tool_up_bar, menu) // + ikonunu menu xml'den al
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Menü item tıklamasını yakala
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addMeal -> {
                // ListFragment'e git
                findNavController().navigate(R.id.action_randomFragment_to_listFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
