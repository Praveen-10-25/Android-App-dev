package com.example.todo_app

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var rvTodos: RecyclerView
    private lateinit var etTodo: EditText
    private lateinit var btnAddTodo: Button
    private lateinit var btnDeleteDone: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvTodos = findViewById(R.id.rvTodos)
        etTodo = findViewById(R.id.etTodo)
        btnAddTodo = findViewById(R.id.BtnAddTodo)
        btnDeleteDone = findViewById(R.id.btnDeleteDone)

        val todoList = mutableListOf(
            Todo("Understanding RecyclerView", false, "21/07/2025"),
            Todo("Understanding Adapter", false, "22/07/2025"),
            Todo("Learn Layouts", false, "23/07/2025"),
        )

        todoAdapter = TodoAdapter(todoList)
        rvTodos.adapter = todoAdapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            if (title.isNotBlank()) {
                val calendar = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    this,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = "$dayOfMonth/${month + 1}/$year"
                        val todo = Todo(title, false, selectedDate)
                        todoList.add(todo)
                        todoAdapter.notifyItemInserted(todoList.size - 1)
                        etTodo.text.clear()
                        hideKeyboard()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }
        }
        btnDeleteDone.setOnClickListener {
            val newList = todoList.filter { !it.isChecked }.toMutableList()
            todoList.clear()
            todoList.addAll(newList)
            todoAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
