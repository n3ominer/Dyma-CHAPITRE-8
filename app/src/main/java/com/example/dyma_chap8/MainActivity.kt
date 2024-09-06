package com.example.dyma_chap8

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dyma_chap8.network.RetrofitClient
import com.example.dyma_chap8.network.dto.TodoDto
import com.example.dyma_chap8.network.dto.UserDto
import com.example.dyma_chap8.network.mapper.mapTodoDtoToAppModel
import com.example.dyma_chap8.network.mapper.mapUserDtoToAppModel
import com.example.dyma_chap8.network.services.TodoServices
import com.example.dyma_chap8.network.services.UserServices
import com.example.dyma_chap8.views.TodosAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var todoRv: RecyclerView
    lateinit var todoRvAdapter: TodosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTodoRv()
        fetchUsers()
    }

    private fun setupTodoRv() {
        this.todoRv = findViewById(R.id.todo_recycler_view)
        this.todoRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchUsers() {
        val todoService = RetrofitClient.instance.create(UserServices::class.java)
        val call = todoService.getAllUsers()

        call.enqueue(object : Callback<List<UserDto>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<UserDto>>, response: Response<List<UserDto>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { users ->
                        val todos = users[0].todos.map { mapTodoDtoToAppModel(it) }
                        this@MainActivity.todoRvAdapter = TodosAdapter(todos)
                        this@MainActivity.todoRv.adapter = this@MainActivity.todoRvAdapter
                        this@MainActivity.todoRvAdapter.notifyDataSetChanged()
                    }
                    Log.d("Récupération succés", "Succès")
                } else {
                    Log.d("Récupération echec", "Echec")
                }
            }

            override fun onFailure(call: Call<List<UserDto>>, t: Throwable) {
                Log.d("Erreur réseau", t.message ?: "Erreur")
            }
        })
    }
}