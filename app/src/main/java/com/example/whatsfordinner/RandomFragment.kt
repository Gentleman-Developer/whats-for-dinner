package com.example.whatsfordinner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsfordinner.databinding.FragmentListBinding
import com.example.whatsfordinner.databinding.FragmentRandomBinding

class RandomFragment : Fragment() {
    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}