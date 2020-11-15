package com.example.uidesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navigation: BottomNavigationView
    private lateinit var moreNavHost: View
    private lateinit var homeNavHost: View
    private lateinit var categoryNavHost: View
    private lateinit var profileNavHost: View
    private lateinit var historyNavHost: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigation = findViewById(R.id.bottomMenu)
        homeNavHost = findViewById(R.id.navHostHomeFragment)
        moreNavHost = findViewById(R.id.navHostMoreFragment)
        categoryNavHost = findViewById(R.id.navHostCategoryFragment)
        profileNavHost = findViewById(R.id.navHostProfileFragment)
        historyNavHost = findViewById(R.id.navHostHistoryFragment)
        navController = Navigation.findNavController(this, R.id.navHostHomeFragment)

        moreNavHost.visibility = View.GONE
        categoryNavHost.visibility = View.GONE
        profileNavHost.visibility = View.GONE
        historyNavHost.visibility = View.GONE
        navigation.selectedItemId = R.id.homeFragment

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostHomeFragment)
                    homeNavHost.visibility = View.VISIBLE
                    categoryNavHost.visibility = View.GONE
                    moreNavHost.visibility = View.GONE
                    profileNavHost.visibility = View.GONE
                    historyNavHost.visibility = View.GONE
                    true
                }
                R.id.moreFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostMoreFragment)
                    homeNavHost.visibility = View.GONE
                    categoryNavHost.visibility = View.GONE
                    moreNavHost.visibility = View.VISIBLE
                    profileNavHost.visibility = View.GONE
                    historyNavHost.visibility = View.GONE
                    true
                }
                R.id.categoryFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostCategoryFragment)
                    homeNavHost.visibility = View.GONE
                    categoryNavHost.visibility = View.VISIBLE
                    moreNavHost.visibility = View.GONE
                    profileNavHost.visibility = View.GONE
                    historyNavHost.visibility = View.GONE
                    true
                }
                R.id.profileFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostProfileFragment)
                    homeNavHost.visibility = View.GONE
                    categoryNavHost.visibility = View.GONE
                    moreNavHost.visibility = View.GONE
                    profileNavHost.visibility = View.VISIBLE
                    historyNavHost.visibility = View.GONE
                    true
                }
                R.id.historyFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostHistoryFragment)
                    navController.addOnDestinationChangedListener { controller, destination, arguments ->
                        if (arguments?.getBoolean("isFullScreen") == true) {
                            navigation.visibility = View.GONE
                        } else {
                            navigation.visibility = View.VISIBLE
                        }
                    }
                    homeNavHost.visibility = View.GONE
                    categoryNavHost.visibility = View.GONE
                    moreNavHost.visibility = View.GONE
                    profileNavHost.visibility = View.GONE
                    historyNavHost.visibility = View.VISIBLE
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed()
        }
    }
}