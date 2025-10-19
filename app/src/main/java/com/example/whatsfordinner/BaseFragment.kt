package com.example.whatsfordinner

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// 🔹 Tüm fragmentlerin ortak parent’ı
open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Root layout’a gradient arka plan uygula
        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.background_gradient)
    }
}
