package com.example.fragmentexercise.api

import com.example.fragmentexercise.data.RegisterRequest
import com.example.fragmentexercise.data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}