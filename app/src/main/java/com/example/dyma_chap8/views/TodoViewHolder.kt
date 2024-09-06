package com.example.dyma_chap8.views

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dyma_chap8.R

class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val todoTitle: TextView = itemView.findViewById(R.id.todo_title_tv)
    val todoDescription: TextView = itemView.findViewById(R.id.todo_description_tv)
    val todoCheckBox: CheckBox = itemView.findViewById(R.id.todo_check_box)
}