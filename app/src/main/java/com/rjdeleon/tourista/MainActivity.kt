package com.rjdeleon.tourista

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(appToolbar)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.navigation_fragment), mainDrawerLayout)
    }

    override fun onBackPressed() {
        if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.navigation_fragment)

        /* Update action bar title when navigating */
        setupActionBarWithNavController(navController, mainDrawerLayout)

        /* Handle navigation drawer item clicks */
        mainNavigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mainDrawerLayout.closeDrawers()
            true
        }

        /* Setup navigation graph with drawer items */
        setupWithNavController(mainNavigationView, navController)
    }
}
