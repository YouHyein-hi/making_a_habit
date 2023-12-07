package com.making.making_a_habit.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.making.making_a_habit.databinding.ItemHabitlistBinding
import com.example.data.room.entity.HabitEntity
import com.example.domain.model.HabitData

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    lateinit var onMainItemClick : (HabitData) -> Unit
    private lateinit var binding : ItemHabitlistBinding
    private var habit: ArrayList<HabitData> = arrayListOf()

    var habitList = mutableListOf<HabitData>()
        set(value) {
            field = value.reversed().toMutableList()
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemHabitlistBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(habit: HabitData) {
            binding.habitNameTextItemmain.text = habit.name
            binding.habitDateStartTextItemmain.text = habit.dateStart
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

            /*
            itemView.setOnLongClickListener {
                onMainItemClick(habit)
                true
            }
             */
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        binding = ItemHabitlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(habit[position])
    }

    override fun getItemCount(): Int {
        return habit.size
    }



    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabit(contacts: List<HabitData>) {
        habit.clear()
        contacts.forEach { item->
            if(!item.complete){
                habit.add(item)
            }
        }
        notifyDataSetChanged()
    }


}