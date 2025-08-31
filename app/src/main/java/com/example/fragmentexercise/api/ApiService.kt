package com.example.fragmentexercise.api

import com.example.fragmentexercise.data.ForgotPasswordRequest
import com.example.fragmentexercise.data.ForgotPasswordResponse
import com.example.fragmentexercise.data.LoginRequest
import com.example.fragmentexercise.data.LoginResponse
import com.example.fragmentexercise.data.LogoutResponse
import com.example.fragmentexercise.data.RegisterRequest
import com.example.fragmentexercise.data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>

    @POST("api/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>
}