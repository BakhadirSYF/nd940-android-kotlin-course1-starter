package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        setSupportActionBar(findViewById(R.id.toolbar))

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
