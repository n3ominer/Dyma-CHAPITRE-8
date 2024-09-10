package com.example.dyma_chap8.viewmodels

import android.content.Context
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
    val error: MutableLiveData<String> = MutableLiveData()

    fun getTodos(owner: LifecycleOwner, context: Context) {
        this.todoRepo.fetchUsers(context)
        this.todoRepo.todosLiveData.observe(owner) { apiResponse ->
            if (apiResponse.data != null) {
                // On a de la donnée
                val users = apiResponse.data
                if (users.isNotEmpty()) {
                    val todos = users[0].todos.map { mapTodoDtoToAppModel(it) }
                    this._todos.value = todos
                } else {
                    //this._todos.value = listOf()
                    this.error.value = apiResponse.error
                }
            } else if (apiResponse.error != null){
                this.error.value = apiResponse.error
            }
        }
    }

}