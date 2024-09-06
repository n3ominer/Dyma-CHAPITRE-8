package com.example.dyma_chap8.data.model

import com.example.dyma_chap8.network.dto.TodoDto

data class User(val id: Int, val name: String, val todos: List<Todo>)
