package com.example.fragmentexercise

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexercise.botnav.DashboardFragment
import com.example.fragmentexercise.botnav.HistoryFragment
import com.example.fragmentexercise.botnav.HomeFragment
import com.example.fragmentexercise.botnav.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var indicatorStrip: View
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicatorStrip = findViewById(R.id.indicatorStrip)
        bottomNav = findViewById(R.id.bottom_navigation)

        // Set default fragment dan posisi strip
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        bottomNav.post { moveStripToIndex(0) }

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