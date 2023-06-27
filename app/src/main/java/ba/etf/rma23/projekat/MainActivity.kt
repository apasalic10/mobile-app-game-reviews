package ba.etf.rma23.projekat

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.etf.rma23.projekat.data.repositories.*
import com.example.rma23_19079_videogames.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val config: Configuration = baseContext.resources.configuration
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_details, GameDetailsFragment()).commit()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_home, HomeFragment()).commit()
        } else {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
            navView.setupWithNavController(navController)
            navController.navigate(R.id.homeFragment)
        }

    }



}


