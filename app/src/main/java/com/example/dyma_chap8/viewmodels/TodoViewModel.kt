package com.example.dyma_chap8.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dyma_chap8.data.model.Todo
import com.example.dyma_chap8.network.mapper.mapTodoDtoToAppModel
import repositories.TodoRepository

class TodoViewModel: ViewModel() {
    private val todoRepo = TodoRepository()
    private val _todos = MutableLiveData<List<Todo>>()

    val todos : LiveData<List<Todo>> get() = _todos


    fun getTodos(owner: LifecycleOwner) {
        this.todoRepo.fetchUsers()
        this.todoRepo.todosLiveData.observe(owner) {
            val todos = it[0].todos.map { mapTodoDtoToAppModel(it) }
            this._todos.value = todos
        }
    }

}