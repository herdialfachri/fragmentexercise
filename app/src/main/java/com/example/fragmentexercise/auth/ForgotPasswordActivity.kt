package com.example.fragmentexercise.auth

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexercise.R
import com.example.fragmentexercise.api.ApiClient
import com.example.fragmentexercise.data.ForgotPasswordRequest
import com.example.fragmentexercise.data.ForgotPasswordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnReset: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.etEmail)
        btnReset = findViewById(R.id.btnReset)
        progressBar = findViewById(R.id.progressBar)
        backButton = findViewById(R.id.backBtn)

        backButton.setOnClickListener {
            onBackPressed()
        }

        btnReset.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            btnReset.isEnabled = false

            val request = ForgotPasswordRequest(email)

            ApiClient.instance.forgotPassword(request).enqueue(object : Callback<ForgotPasswordResponse> {
                override fun onResponse(call: Call<ForgotPasswordResponse>, response: Response<ForgotPasswordResponse>) {
                    progressBar.visibility = View.GONE
                    btnReset.isEnabled = true

                    if (response.isSuccessful && response.body() != null) {
                        val res = response.body()!!
                        Toast.makeText(this@ForgotPasswordActivity, res.message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@ForgotPasswordActivity, "Failed: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    btnReset.isEnabled = true
                    Toast.makeText(this@ForgotPasswordActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}