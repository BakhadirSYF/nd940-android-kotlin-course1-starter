package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        setSupportActionBar(binding.toolbar)

        val navController = this.findNavController(R.id.navHostFragment)

        var fragSet = mutableSetOf<Int>()
        fragSet.add(R.id.loginFragment)
        fragSet.add(R.id.welcomeFragment)
        fragSet.add(R.id.instructionsFragment)
        fragSet.add(R.id.shoeListFragment)
        fragSet.add(R.id.shoeDetailFragment)

        appBarConfiguration = AppBarConfiguration(fragSet, null)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
                || super.onSupportNavigateUp()
    }
}
