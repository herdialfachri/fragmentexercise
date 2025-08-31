package com.example.fragmentexercise.botnav

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentexercise.R
import com.example.fragmentexercise.SettingActivity

class ProfileFragment : Fragment() {

    private lateinit var preferences: SharedPreferences
    private lateinit var settingsPref: SharedPreferences
    private lateinit var usernameTv: TextView
    private lateinit var emailTv: TextView
    private lateinit var settingBtn: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        usernameTv = view.findViewById(R.id.usernameTv)
        emailTv = view.findViewById(R.id.emailTv)
        settingBtn = view.findViewById(R.id.settingBtn)

        settingBtn.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
        }

        preferences = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        settingsPref = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        // initial set
        updateUserInfo()

        return view
    }

    override fun onResume() {
        super.onResume()
        // refresh setiap kali balik ke fragment
        updateUserInfo()
    }

    private fun updateUserInfo() {
        usernameTv.text = preferences.getString("USER_NAME", "User")
        emailTv.text = preferences.getString("USER_EMAIL", "user@example.com")
    }
}