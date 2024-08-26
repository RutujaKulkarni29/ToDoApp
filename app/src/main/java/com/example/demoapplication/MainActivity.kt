package com.example.demoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter : TodoAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())

        /// Attach TodoAdapter to our Recyclerview
        binding.rvTodoItems.adapter = todoAdapter

        /// LayoutManager - Defines how the items are arranged in the list
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        ///Onclick listeners for the buttons

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }

        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}
