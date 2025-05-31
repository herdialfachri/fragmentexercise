package com.example.fragmentexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexercise.botnav.DashboardFragment
import com.example.fragmentexercise.botnav.HomeFragment
import com.example.fragmentexercise.botnav.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Default Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DashboardFragment())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}