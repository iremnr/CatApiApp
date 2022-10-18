package com.example.catapiapp.model


import com.google.gson.annotations.SerializedName

data class NewCatModelItem(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)