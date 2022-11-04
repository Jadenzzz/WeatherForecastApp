package com.cos30017.weatherapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.databinding.ActivityMainBinding
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.repository.ForecastRepository
import com.cos30017.weatherapp.viewmodels.CurrentViewModel
import com.cos30017.weatherapp.viewmodels.ForecastViewModel
import com.cos30017.weatherapp.viewmodels.ForecastViewModelProviderFactory
import com.cos30017.weatherapp.viewmodels.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: CurrentViewModel
    lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val currentRepository = CurrentWeatherRepository()
        val forecastRepository = ForecastRepository()
        val viewModelProviderFactory = ViewModelProviderFactory(application, currentRepository)
        val forecastviewModelProviderFactory = ForecastViewModelProviderFactory(application, forecastRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[CurrentViewModel::class.java]
        forecastViewModel = ViewModelProvider(this, forecastviewModelProviderFactory)[ForecastViewModel::class.java]

        //implement navigation bar
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }
}