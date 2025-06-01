package com.example.fragmentexercise.botnav

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fragmentexercise.R

class ProfileFragment : Fragment() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchTheme: Switch
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        switchTheme = view.findViewById(R.id.switchTheme)
        preferences = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        val isNightMode = preferences.getBoolean("night_mode", false)
        switchTheme.isChecked = isNightMode

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            // Simpan nama fragment terakhir
            preferences.edit().putString("last_fragment", "profile").apply()

            // Simpan mode malam
            preferences.edit().putBoolean("night_mode", isChecked).apply()

            // Terapkan mode malam
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        return view
    }
}