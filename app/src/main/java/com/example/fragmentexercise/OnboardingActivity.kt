package com.example.fragmentexercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentexercise.onboarding.OnboardingAdapter

class OnboardingActivity : AppCompatActivity() {

    private lateinit var adapter: OnboardingAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        adapter = OnboardingAdapter(this)
        viewPager.adapter = adapter
    }

    fun nextPage() {
        if (viewPager.currentItem < adapter.itemCount - 1) {
            viewPager.currentItem += 1
        } else {
            // Pindah ke LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}