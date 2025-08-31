package com.example.fragmentexercise.data

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String,
    val role_id: Int,
    val profile_photo: String?,
    val location: String?,
    val phone: String?,
    val specialization: String?
)