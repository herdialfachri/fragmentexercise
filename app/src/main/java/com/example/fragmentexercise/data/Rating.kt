package com.example.fragmentexercise.data

import com.google.gson.annotations.SerializedName

data class Rating(
    val id: Int,
    @SerializedName("service_id") val serviceId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("order_id") val orderId: Int,
    val rating: Int,
    val comment: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)