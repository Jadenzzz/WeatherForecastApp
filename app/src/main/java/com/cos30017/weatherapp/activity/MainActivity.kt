package com.cos30017.weatherapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.connectivity.CheckConnection
import com.cos30017.weatherapp.data.db.database.CurrentDatabase
import com.cos30017.weatherapp.databinding.ActivityMainBinding
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.viewmodels.CurrentViewModel
import com.cos30017.weatherapp.viewmodels.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: CurrentViewModel
//    private lateinit var checkNetworkConnection: CheckConnection
//    private lateinit var vConnection: TextView
//    var isConnected: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        callNetworkConnection()


        val currentRepository = CurrentWeatherRepository(CurrentDatabase(this))
        val viewModelProviderFactory = ViewModelProviderFactory(application, currentRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[CurrentViewModel::class.java]
        
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        navController=Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }
//    private fun callNetworkConnection() {
//        checkNetworkConnection = CheckConnection(application)
//        checkNetworkConnection.observe(this) { isConnected ->
//            if (isConnected) {
//                this@MainActivity.isConnected = true
//                vConnection.text = ""
//            } else {
//                this@MainActivity.isConnected = false
//                vConnection.text = "No internet connection"
//            }
//        }
//
//    }
}