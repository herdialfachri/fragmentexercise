package com.example.fragmentexercise.botnav

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fragmentexercise.R
import com.example.fragmentexercise.api.ApiClient
import com.example.fragmentexercise.auth.LoginActivity
import com.example.fragmentexercise.data.LogoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var switchTheme: Switch
    private lateinit var preferences: SharedPreferences
    private lateinit var settingsPref: SharedPreferences
    private lateinit var usernameTv: TextView
    private lateinit var emailTv: TextView
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inisialisasi views
        switchTheme = view.findViewById(R.id.switchTheme)
        usernameTv = view.findViewById(R.id.usernameTv)
        emailTv = view.findViewById(R.id.emailTv)
        btnLogout = view.findViewById(R.id.btnLogout)

        // SharedPreferences
        preferences = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        settingsPref = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        // Tampilkan username & email
        usernameTv.text = preferences.getString("USER_NAME", "User")
        emailTv.text = preferences.getString("USER_EMAIL", "user@example.com")

        // Mode malam
        val isNightMode = settingsPref.getBoolean("night_mode", false)
        switchTheme.isChecked = isNightMode

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            // Simpan mode malam
            settingsPref.edit().putBoolean("night_mode", isChecked).apply()

            // Simpan fragment terakhir supaya tetap di Profile saat recreate
            settingsPref.edit().putString("last_fragment", "profile").apply()

            // Terapkan mode malam
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Logout dengan dialog konfirmasi
        btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Keluar")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ -> performLogout() }
                .setNegativeButton("Batal", null)
                .show()
        }

        return view
    }

    private fun performLogout() {
        val token = preferences.getString("TOKEN", null)
        if (token == null) {
            Toast.makeText(requireContext(), "Token not found!", Toast.LENGTH_SHORT).show()
            return
        }

        ApiClient.instance.logout("Bearer $token").enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val logoutResponse = response.body()!!

                    // Hapus semua data user & mode malam
                    preferences.edit().clear().apply()
                    settingsPref.edit().clear().apply()

                    // Kembali ke login
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()

                    Toast.makeText(requireContext(), logoutResponse.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Logout failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}