package com.example.fragmentexercise

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fragmentexercise.api.ApiClient
import com.example.fragmentexercise.auth.LoginActivity
import com.example.fragmentexercise.data.LogoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchTheme: Switch
    private lateinit var btnLogout: Button
    private lateinit var preferences: SharedPreferences
    private lateinit var settingsPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setting)

        backButton = findViewById(R.id.backBtn)
        switchTheme = findViewById(R.id.switchTheme)
        btnLogout = findViewById(R.id.btnLogout)

        preferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE)

        // back button
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("OPEN_FRAGMENT", "profile")
            startActivity(intent)
            finish()
        }

        // =====================
        // Switch Dark Mode
        // =====================
        val isNightMode = settingsPref.getBoolean("night_mode", false)
        switchTheme.isChecked = isNightMode

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            settingsPref.edit().putBoolean("night_mode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // =====================
        // Logout Button
        // =====================
        btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ -> performLogout() }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    private fun performLogout() {
        val token = preferences.getString("TOKEN", null)
        if (token == null) {
            Toast.makeText(this, "Token not found!", Toast.LENGTH_SHORT).show()
            return
        }

        ApiClient.instance.logout("Bearer $token").enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val logoutResponse = response.body()!!

                    preferences.edit().clear().apply()
                    settingsPref.edit().clear().apply()

                    val intent = Intent(this@SettingActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                    Toast.makeText(this@SettingActivity, logoutResponse.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SettingActivity, "Logout failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                Toast.makeText(this@SettingActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}