package com.example.whatsfordinner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.whatsfordinner.databinding.ActivityMainBinding

// 🔹 MainActivity: Uygulamanın giriş noktası, toolbar ve navigation host'u yönetir
class MainActivity : AppCompatActivity() {

    // 🔹 ViewBinding: XML layout'u ile güvenli erişim sağlar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔹 Layout'u binding ile inflate et
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Toolbar'ı ActionBar olarak ayarla
        setSupportActionBar(binding.toolbar)

        // 🔹 NavHostFragment'i bul ve NavController al
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 🔹 AppBarConfiguration ile "ana fragmentleri" belirt
        // Ana fragmentlerde geri ok tuşu görünmez
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.randomFragment) // Ana ekran fragment ID'si
        )

        // 🔹 Toolbar ile NavController'ı bağla
        // Böylece fragment geçişlerinde title ve geri ok otomatik yönetilir
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // 🔹 ActionBar üzerindeki geri ok tuşuna basıldığında çağrılır
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 🔹 Eğer NavController geri gidiyorsa onu kullan, yoksa default davranışı uygula
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
