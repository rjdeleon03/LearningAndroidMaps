package com.rjdeleon.tourista

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mNavController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* App bar configuration for proper drawer navigation */
        mAppBarConfiguration = AppBarConfiguration
                .Builder(setOf(
                        R.id.myLocationFragment,
                        R.id.weatherFragment,
                        R.id.currencyConverterFragment,
                        R.id.worldClockFragment
                ))
                .setDrawerLayout(mainDrawerLayout)
                .build()

        /* Setup nav controller */
        mNavController =  findNavController(R.id.navigation_fragment);

        setSupportActionBar(appToolbar)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.navigation_fragment), mAppBarConfiguration)
    }

    override fun onBackPressed() {
        if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {

        /* Update action bar title when navigating */
        setupActionBarWithNavController(this, mNavController, mAppBarConfiguration)

        /* Setup navigation graph with drawer items */
        setupWithNavController(mainNavigationView, mNavController)

        /* Handle navigation drawer item clicks */
        mainNavigationView.menu.getItem(0).isChecked = true
        mainNavigationView.setNavigationItemSelectedListener { menuItem ->

            if (!menuItem.isChecked) {

                menuItem.isChecked = true
                when (menuItem.itemId) {
                    R.id.menu_myLocationFragment ->
                        mNavController.navigate(R.id.myLocationFragment)
                    R.id.menu_weatherFragment ->
                        mNavController.navigate(R.id.weatherFragment)
                    R.id.menu_currencyConverterFragment ->
                        mNavController.navigate(R.id.currencyConverterFragment)
                    R.id.menu_worldClockFragment ->
                        mNavController.navigate(R.id.worldClockFragment)
                }
            }

            mainDrawerLayout.closeDrawers()
            true
        }
    }
}
