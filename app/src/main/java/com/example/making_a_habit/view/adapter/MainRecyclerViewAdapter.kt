package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.ItemMainBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.DetailHabitActivity
import com.example.making_a_habit.view.MainActivity
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DeletedialogViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

class MainRecyclerViewAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private var habit: ArrayList<Habit> = arrayListOf()


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

        private val context = binding.root.context

        @SuppressLint("ResourceAsColor")
        fun bind(habit: Habit) {
            binding.habitNameTextItemmain.text = habit.habitName
            binding.habitDateStartTextItemmain.text = habit.habitDateStart
            binding.habitRoundFullTextItemmain.text = habit.habitRoundFull.toString()

            /***** progressBar 관련 코드 *****/
            if(habit.habitPeriodNum == 3){
                binding.habitRoundFullProgressbarItemmain.max = 3
                binding.habitRoundFullProgressbarItemmain.progress = habit.habitRoundFull
            }
            else if(habit.habitPeriodNum == 15){
                binding.habitRoundFullProgressbarItemmain.max = 15
                binding.habitRoundFullProgressbarItemmain.progress = habit.habitRoundFull
            }
            else if(habit.habitPeriodNum == 30){
                binding.habitRoundFullProgressbarItemmain.max = 30
                binding.habitRoundFullProgressbarItemmain.progress = habit.habitRoundFull
            }
            when(habit.habitColor){
                "red" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#FFAEAE"))
                "yellow" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#FFE8AE"))
                "green" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#B1CFD1"))
                "blue" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#AED8FF"))
                "gray" -> binding.habitRoundFullProgressbarItemmain.setIndicatorColor(Color.parseColor("#CECECE"))
            }

            itemView.setOnClickListener {
                mainItemClick(habit)

                val intent = Intent(context, DetailHabitActivity::class.java)
                intent.putExtra("data", habit.habitId);
                intent.run { context.startActivity(this) }

                val fragmentDeleteDialog : deleteDialogFragment = deleteDialogFragment()
                val bundle : Bundle = Bundle()
                if (habit.habitId != null) {
                    bundle.putInt("deleteHabitId", habit.habitId)
                }
                fragmentDeleteDialog.arguments = bundle

            }

            itemView.setOnLongClickListener {
                mainItemClick(habit)
                true
            }
        }
    }

    /***** 추가하는 부분 *****/
    @SuppressLint("NotifyDataSetChanged")
    fun sethabit(contacts: List<Habit>) {
        habit.clear()
        contacts.forEach { item->
            if(!item.habitComplete){
                habit.add(item)
            }
        }
        notifyDataSetChanged()
    }
}