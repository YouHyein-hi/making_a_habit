package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.databinding.ItemRoundfull3Binding
import com.example.making_a_habit.model.Habit

class Habit3RoundAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<Habit3RoundAdapter.ViewHolder>() {

    //private var habit: List<Habit> = listOf()
    private val list = listOf(1)

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val binding = ItemRoundfull3Binding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        println("size : " + list.size)
        return list.size
    }

    override fun onBindViewHolder(viewHolder: Habit3RoundAdapter.ViewHolder, position: Int) {
        viewHolder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemRoundfull3Binding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(habit: Int) {
            binding.invisibletext3.text = 1.toString()
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabitPeriod() {
        notifyDataSetChanged()
    }



}