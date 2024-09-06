package com.example.dyma_chap8.network.services

import com.example.dyma_chap8.network.dto.TodoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoServices {

    @GET("todos/all")
    fun getAllTodos(): Call<List<TodoDto>>

    @GET("todos/byid/{id}")
    fun getOneTodoById(@Path("id") todoId: Int): Call<TodoDto>

    fun addOneTodo()
}