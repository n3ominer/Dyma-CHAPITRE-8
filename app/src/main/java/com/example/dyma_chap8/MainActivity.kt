package com.example.dyma_chap8

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dyma_chap8.viewmodels.TodoViewModel
import com.example.dyma_chap8.views.TodosAdapter

class MainActivity : AppCompatActivity() {

    lateinit var todoRv: RecyclerView
    lateinit var todoRvAdapter: TodosAdapter

    lateinit var todoViewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTodoRv()
        this.todoViewModel = TodoViewModel()

        this.todoViewModel.getTodos(this)
        this.observeTodoLiveData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeTodoLiveData() {
        this.todoViewModel.todos.observe(this) {
            this@MainActivity.todoRvAdapter = TodosAdapter(it)
            this@MainActivity.todoRv.adapter = this@MainActivity.todoRvAdapter
            this@MainActivity.todoRvAdapter.notifyDataSetChanged()
        }

        this.todoViewModel.error.observe(this) {error ->
            Toast.makeText(this, "Erreur: $error", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupTodoRv() {
        this.todoRv = findViewById(R.id.todo_recycler_view)
        this.todoRv.layoutManager = LinearLayoutManager(this)
    }

}