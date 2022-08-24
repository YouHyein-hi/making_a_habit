package com.example.making_a_habit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ItemMainBinding
import com.example.making_a_habit.databinding.SetupPageBinding
import com.example.making_a_habit.model.Habit

class MainRecyclerViewAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private var habit: List<Habit> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return habit.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(habit[position])
    }

    inner class ViewHolder(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.habitNameTextItemmain.text = habit.habitName
            binding.habitDateStartTextItemmain.text = habit.habitDateStart
            binding.habitRoundFullTextItemmain.text = habit.habitRoundFull.toString()

            itemView.setOnClickListener {
                mainItemClick(habit)
            }

            itemView.setOnLongClickListener {
                mainItemClick(habit)
                true
            }
        }
    }

    /***** 추가하는 부분 *****/
    fun sethabit(contacts: List<Habit>) {
        this.habit = contacts
        notifyDataSetChanged()
    }

}