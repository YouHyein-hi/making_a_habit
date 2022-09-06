package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.databinding.ItemRoundfullBinding
import com.example.making_a_habit.model.Habit

class HabitRoundAdapter (val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<HabitRoundAdapter.ViewHolder>() {

    private val list = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitRoundAdapter.ViewHolder {
        val binding = ItemRoundfullBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: HabitRoundAdapter.ViewHolder, position: Int) {


        viewHolder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemRoundfullBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(habit: Int) {
            /***** DetailHabitActivity로 부터 받기 *****/

            binding.invisibletext.text = 1.toString()
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabitPeriod(count: Int?) {
        count?: return
        list.clear()
        for (i in 1..count){
            list.add(i)
        }

        notifyDataSetChanged()
    }
}