package com.example.fragmentexercise.data

data class LoginResponse(
    val message: String,
    val user: User,
    val token: String
)