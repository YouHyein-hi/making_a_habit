package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.databinding.ItemRoundfull30Binding
import com.example.making_a_habit.model.Habit

class Habit30RoundAdapter (val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<Habit30RoundAdapter.ViewHolder>() {

    //private var habit: List<Habit> = listOf()
    private val list = listOf(1)

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val binding = ItemRoundfull30Binding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        println("size : " + list.size)
        return list.size
    }

    override fun onBindViewHolder(viewHolder: Habit30RoundAdapter.ViewHolder, position: Int) {
        viewHolder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemRoundfull30Binding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(habit: Int) {
            binding.invisibletext30.text = 1.toString()
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabitPeriod() {
        notifyDataSetChanged()
    }



}