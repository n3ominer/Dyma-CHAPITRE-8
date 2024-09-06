package com.example.dyma_chap8.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dyma_chap8.R
import com.example.dyma_chap8.data.model.Todo

class TodosAdapter(val todos: List<Todo>): RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_cell, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = this.todos[position]

        holder.todoTitle.text = todo.title
        holder.todoDescription.text = todo.description
        holder.todoCheckBox.isChecked = todo.done
    }
}