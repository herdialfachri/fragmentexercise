package com.example.fragmentexercise

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fragmentexercise.botnav.DashboardFragment
import com.example.fragmentexercise.botnav.HistoryFragment
import com.example.fragmentexercise.botnav.HomeFragment
import com.example.fragmentexercise.botnav.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var indicatorStrip: View
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ambil preferensi terlebih dahulu
        val preferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val isNightMode = preferences.getBoolean("night_mode", false)
        val lastFragment = preferences.getString("last_fragment", "home")

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicatorStrip = findViewById(R.id.indicatorStrip)
        bottomNav = findViewById(R.id.bottom_navigation)

        // Tentukan fragment awal berdasarkan preferensi
        val initialIndex = when (lastFragment) {
            "profile" -> 3
            "history" -> 2
            "dashboard" -> 1
            else -> 0
        }

        val initialFragment = when (lastFragment) {
            "profile" -> ProfileFragment()
            "history" -> HistoryFragment()
            "dashboard" -> DashboardFragment()
            else -> HomeFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, initialFragment)
            .commit()

        bottomNav.selectedItemId = when (initialIndex) {
            1 -> R.id.nav_dashboard
            2 -> R.id.nav_history
            3 -> R.id.nav_profile
            else -> R.id.nav_home
        }

        bottomNav.post { moveStripToIndex(initialIndex) }

        // Reset fragment terakhir agar tidak berulang terus
        preferences.edit().remove("last_fragment").apply()

        // Setup navigasi
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    moveStripToIndex(0)
                    true
                }
                R.id.nav_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DashboardFragment())
                        .commit()
                    moveStripToIndex(1)
                    true
                }
                R.id.nav_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HistoryFragment())
                        .commit()
                    moveStripToIndex(2)
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    moveStripToIndex(3)
                    true
                }
                else -> false
            }
        }
    }

    private fun moveStripToIndex(index: Int) {
        val menuSize = bottomNav.menu.size()
        val itemWidth = bottomNav.width / menuSize
        val targetX = index * itemWidth

        indicatorStrip.animate()
            .translationX(targetX.toFloat())
            .setDuration(200)
            .start()
    }
}