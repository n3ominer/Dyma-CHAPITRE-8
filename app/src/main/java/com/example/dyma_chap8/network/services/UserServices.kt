package com.example.dyma_chap8.network.services

import com.example.dyma_chap8.network.dto.UserDto
import retrofit2.Call
import retrofit2.http.GET

interface UserServices {

    @GET("n3ominer/mock-server/users")
    fun getAllUsers(): Call<List<UserDto>>
}