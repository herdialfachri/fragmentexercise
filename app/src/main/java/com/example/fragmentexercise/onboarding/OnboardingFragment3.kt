package com.example.fragmentexercise.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fragmentexercise.OnboardingActivity
import com.example.fragmentexercise.R
import com.example.fragmentexercise.auth.LoginActivity
import com.example.fragmentexercise.helper.SharedPrefManager

class OnboardingFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding3, container, false)

        val btnGetStarted = view.findViewById<Button>(R.id.btnNext)
        btnGetStarted.setOnClickListener {
            val pref = SharedPrefManager(requireContext())
            pref.setFirstTimeLaunch(false)

            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }

        return view
    }
}