package com.example.dyma_chap8

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dyma_chap8.network.RetrofitClient
import com.example.dyma_chap8.network.dto.TodoDto
import com.example.dyma_chap8.network.services.TodoServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun fetchTodos() {
        val todoService = RetrofitClient.instance.create(TodoServices::class.java)
        val call = todoService.getAllTodos()

        call.enqueue(object : Callback<List<TodoDto>> {
            override fun onResponse(call: Call<List<TodoDto>>, response: Response<List<TodoDto>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        // Setup recycler view
                    }
                    Log.d("Récupération succés", "Succès")
                } else {
                    Log.d("Récupération echec", "Echec")
                }
            }

            override fun onFailure(call: Call<List<TodoDto>>, t: Throwable) {
                Log.d("Erreur réseau", t.message ?: "Erreur")
            }
        })
    }
}