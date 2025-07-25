package com.example.weekly_projectfunctional_todo_list_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    var todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
        val tvDueDate: TextView = itemView.findViewById(R.id.tvDueDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]

        holder.cbDone.setOnCheckedChangeListener(null)
        holder.tvTitle.text = currentTodo.title
        holder.cbDone.isChecked = currentTodo.isChecked

        holder.tvDueDate.text = currentTodo.dueDate?.let { "Due: $it" } ?: ""


        holder.cbDone.setOnCheckedChangeListener { _, isChecked ->
            currentTodo.isChecked = isChecked
        }
    }
}
