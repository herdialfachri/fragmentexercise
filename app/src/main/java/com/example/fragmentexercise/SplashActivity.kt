package com.example.fragmentexercise

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexercise.helper.SharedPrefManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SharedPrefManager(this)

        if (pref.isFirstTimeLaunch()) {
            // Belum pernah buka onboarding
            startActivity(Intent(this, OnboardingActivity::class.java))
        } else {
            // Sudah pernah, langsung ke Main
            startActivity(Intent(this, MainActivity::class.java))
        }

        finish()
    }
}