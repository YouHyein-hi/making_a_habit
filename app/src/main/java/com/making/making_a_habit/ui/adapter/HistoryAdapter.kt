package com.making.making_a_habit.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.making.making_a_habit.databinding.ItemHabitlistBinding
import com.example.domain.model.HabitData

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    lateinit var onMainItemClick : (HabitData) -> Unit
    private var habit: ArrayList<HabitData> = arrayListOf()

    var habitList = mutableListOf<HabitData>()
        set(value) {
            field = value.reversed().toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val binding = ItemHabitlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return habit.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(habit[position])
    }

    inner class ViewHolder(private val binding: ItemHabitlistBinding): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(habit: HabitData) {
            binding.habitNameTextItemmain.text = habit.name
            val habitDate = habit.dateStart + " ~ " + habit.dateEnd
            binding.habitDateStartTextItemmain.text = habitDate
            binding.habitRoundFullTextItemmain.text = habit.roundFull.toString()

            /***** progressBar 관련 코드 *****/
            if(habit.periodNum == 3){
                binding.habitRoundFullProgressbarItemmain.max = 3
                binding.habitRoundFullProgressbarItemmain.progress = habit.roundFull
            }
            else if(habit.periodNum == 15){
                binding.habitRoundFullProgressbarItemmain.max = 15
                binding.habitRoundFullProgressbarItemmain.progress = habit.roundFull
            }
            else if(habit.periodNum == 30){
                binding.habitRoundFullProgressbarItemmain.max = 30
                binding.habitRoundFullProgressbarItemmain.progress = habit.roundFull
            }
            when(habit.color){
                "red" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#FFAEAE"))
                "yellow" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#FFE8AE"))
                "green" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#B1E6E6"))
                "blue" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#AED8FF"))
                "gray" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#AAAAAA"))
            }

            itemView.setOnClickListener {
                onMainItemClick(habit)
            }
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabit(contacts: List<HabitData>) {
        habit.clear()
        contacts.forEach { item->
            if(item.complete){
                habit.add(item)
            }
        }
        notifyDataSetChanged()
    }


}