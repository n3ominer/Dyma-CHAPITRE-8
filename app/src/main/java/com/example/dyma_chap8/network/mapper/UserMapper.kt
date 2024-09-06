package com.example.dyma_chap8.network.mapper

import com.example.dyma_chap8.data.model.Todo
import com.example.dyma_chap8.data.model.User
import com.example.dyma_chap8.network.dto.TodoDto
import com.example.dyma_chap8.network.dto.UserDto

fun mapUserDtoToAppModel(dto: UserDto): User {
    return User(
        id = dto.id,
        name = dto.name,
        todos = dto.todos.map {
            mapTodoDtoToAppModel(it)
        }
    )
}

fun mapTodoDtoToAppModel(dto: TodoDto): Todo {
    return Todo(
        id = dto.id,
        title = dto.title,
        description = dto.description,
        done = dto.done
    )
}