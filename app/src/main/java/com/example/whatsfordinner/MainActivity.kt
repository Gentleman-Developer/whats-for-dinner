package com.example.whatsfordinner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.whatsfordinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding için property
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding ile layout'u inflate et
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar'ı ActionBar olarak ayarla
        setSupportActionBar(binding.toolbar)

        // NavHostFragment'i bul ve NavController al
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // AppBarConfiguration ile "ana fragmentleri" belirt
        // Ana fragmentlerde geri ok gösterilmez
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.randomFragment) // Ana ekran id'si
        )

        // Toolbar ile NavController'ı bağla
        // Böylece geri ok ve title otomatik yönetilir
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Geri ok tuşuna basıldığında NavController ile geri git
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
