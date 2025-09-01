package com.example.fragmentexercise.data

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("email_verified_at") val emailVerifiedAt: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("role_id") val roleId: Int,
    @SerializedName("profile_photo") val profilePhoto: String?,
    val location: String?,
    val phone: String?,
    val specialization: String?
)