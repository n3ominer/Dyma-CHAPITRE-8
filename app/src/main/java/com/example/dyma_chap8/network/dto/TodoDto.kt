package com.example.dyma_chap8.network.dto

import com.google.gson.annotations.SerializedName

data class TodoDto(
    val id: Int,
    val title: String,
    @SerializedName("desc")
    val description: String,
    val done: Boolean
)
