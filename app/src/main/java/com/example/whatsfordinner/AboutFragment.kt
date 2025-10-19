package com.example.whatsfordinner

// Gerekli Android ve Jetpack importları
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsfordinner.databinding.FragmentAboutBinding
import com.example.whatsfordinner.databinding.FragmentRandomBinding // (Bu satır kullanılmıyor, kaldırılabilir)

// "AboutFragment" — uygulamanın bilgi veya geliştirici bağlantılarını içeren sayfa
class AboutFragment :BaseFragment(R.layout.fragment_list) {

    // View Binding nesnesi (fragment_about.xml dosyasındaki UI bileşenlerine erişim sağlar)
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!! // null olmaması için güvenli çağrı

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment oluşturulurken çağrılır (henüz görünüm yok)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // XML layout’u (fragment_about.xml) inflate edilir
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root // View Binding üzerinden kök görünüm döndürülür
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // "Instagram" butonuna tıklanınca tarayıcıda açılacak bağlantı
        binding.instagramButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://instagram.com/") // 👈 kendi hesabınla değiştir
            )
            startActivity(intent) // Tarayıcı veya Instagram uygulamasını açar
        }

        // "GitHub" butonuna tıklanınca tarayıcıda açılacak bağlantı
        binding.githubButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/") // 👈 kendi GitHub linkinle değiştir
            )
            startActivity(intent)
        }
    }
}
