package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        appBarConfiguration = AppBarConfiguration(navController.graph, null)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}
