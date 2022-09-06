package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ItemRoundfullBinding
import com.example.making_a_habit.model.DetailItem
import com.example.making_a_habit.model.Habit

class DetailHabitAdapter (val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<DetailHabitAdapter.ViewHolder>() {

    private val list = arrayListOf<Int>()
    private var color = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHabitAdapter.ViewHolder {
        val binding = ItemRoundfullBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DetailHabitAdapter.ViewHolder, position: Int) {

        viewHolder.bind(list[position], color)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemRoundfullBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        @SuppressLint("ResourceAsColor")
        fun bind(count: Int, color : String) {
            when(color){
                "red" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_red)
                "yellow" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_yellow)
                "green" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_green)
                "blue" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_blue)
                "gray" -> binding.habitroundfull.setBackgroundResource(R.drawable.checklist_theme_gray)
            }
            binding.habitroundfull.text = count.toString()


            binding.habitroundfull.setOnClickListener{
                binding.habitroundfull.isSelected = true
                // TODO 이거 색 너무 구린데 나중에 바꾸자 색깔 별 해야될 듯!
                binding.habitroundfull.setTextColor(R.color.enabled_text)
            }

            /***** 비활성화일 경우 바꿔주기 *****/
            if(binding.habitroundfull.isEnabled == true){
                binding.habitroundfull.setTextColor(R.color.enabled_text)
            }
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabitPeriod(item: DetailItem) {
        item.count?: return
        item.color?: return
        color = item.color
        list.clear()
        for (i in 1..item.count){
            list.add(i)
        }
        notifyDataSetChanged()
    }


}