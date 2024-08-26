package com.example.demoapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.databinding.ItemTodoBinding

/// Has the logic of how we want to set the data of each list item
/// Takes a list of todos as a parameter
class TodoAdapter(private val todos : MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private lateinit var binding: ItemTodoBinding

    /// View holder - holds the layout of a specific item. With this, the recycler view accomplishes the behaviour we want to have (display only the necessary items)
    /// Parameter - Reference of the view it holds (constraint layout of item_todo.xml)
//    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        /// layoutinflater gets the ref of the view (item_todo.xml) and converts it to kotlin class to be set as the view parameter
//       return TodoViewHolder(LayoutInflater.from(parent.context).inflate(
//               R.layout.item_todo,
//           parent,
//           false
//       ))
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        /// Bind the data from todos list item to the view of the list item. Called when a new viewholder/ list item is visible
        val curTodo = todos[position]
//        holder.itemView.apply {
        holder.binding.apply {
            // Access views using binding
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll{
            todo -> todo.isChecked
        }
        notifyDataSetChanged()
    }
}