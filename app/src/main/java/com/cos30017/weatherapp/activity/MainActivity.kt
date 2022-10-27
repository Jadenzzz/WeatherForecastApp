package com.cos30017.weatherapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.connectivity.CheckConnection
import com.cos30017.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var checkNetworkConnection: CheckConnection
    private lateinit var vConnection: TextView
    var isConnected: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        callNetworkConnection()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController=Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }
    private fun callNetworkConnection() {
        checkNetworkConnection = CheckConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                this@MainActivity.isConnected = true
                vConnection.text = ""
            } else {
                this@MainActivity.isConnected = false
                vConnection.text = "No internet connection"
            }
        }

    }
}