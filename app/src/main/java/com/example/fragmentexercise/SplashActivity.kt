package com.example.fragmentexercise

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexercise.auth.LoginActivity
import com.example.fragmentexercise.helper.SharedPrefManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SharedPrefManager(this)

        // Cek token login di SharedPreferences
        val sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", null)

        when {
            token != null -> {
                // Token ada → langsung ke MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            }
            pref.isFirstTimeLaunch() -> {
                // Pertama kali buka app → ke Onboarding
                startActivity(Intent(this, OnboardingActivity::class.java))
            }
            else -> {
                // Belum login → ke LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        finish()
    }
}