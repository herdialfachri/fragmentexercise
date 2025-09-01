package com.example.fragmentexercise.data

import com.google.gson.annotations.SerializedName

data class Service(
    val id: Int,
    @SerializedName("service_name") val serviceName: String,
    val description: String,
    val price: String,
    @SerializedName("average_rating") val averageRating: String?,
    val user: User,
    val category: Category
)