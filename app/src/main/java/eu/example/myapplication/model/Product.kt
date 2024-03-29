package eu.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val title: String,

    @SerializedName("photo_url")
    val photoUrl: String,

    val price: String,

    val isOnSale: String
)