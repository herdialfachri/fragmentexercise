package com.example.fragmentexercise.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fragmentexercise.OnboardingActivity
import com.example.fragmentexercise.R

class OnboardingFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding2, container, false)

        val btnNext = view.findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            (activity as? OnboardingActivity)?.nextPage()
        }

        return view
    }
}