package com.example.dyma_chap8.network.services

import com.example.dyma_chap8.network.dto.UserDto
import com.example.dyma_chap8.network.response.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserServices {

    @GET("n3ominer/mock-server/users")
    fun getAllUsers(@Header("Autorization") token: String): Call<List<UserDto>>
}