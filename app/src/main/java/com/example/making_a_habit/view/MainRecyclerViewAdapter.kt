package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.ItemMainBinding
import com.example.making_a_habit.model.Habit
import kotlinx.coroutines.NonDisposableHandle.parent

class MainRecyclerViewAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private var habit: List<Habit> = listOf()
    private val limit = 3


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding_mainpage = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        fun limitSize(){
            println("item 3개임!")
            binding_mainpage.creatingPageBtn.isEnabled = false
        }

        /*if(habit.size > limit){
            println("item 3개임!")
            binding_mainpage.creatingPageBtn.isEnabled = false
        }*/

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //if (habit.size > limit) limitSize()

        // 화면에 item 3개만 띄우기 but 생성은 계속됨! DB에 계속 들어감!
        // TODO item 3개 넘을 시 제한 주기 (button 비활성화, button 클릭시 토스트 추가  --> 근데 이걸 Adapter에 하는게 맞나?)
        return Math.min(habit.size, limit)
    }

    /*private fun limitSize() {
        val binding_mainpage = ActivityMainBinding.bind(view)
        println("item 3개임!")
        binding_mainpage.creatingPageBtn.isEnabled = false
    }*/

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(habit[position])
    }

    inner class ViewHolder(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

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
    @SuppressLint("NotifyDataSetChanged")
    fun sethabit(contacts: List<Habit>) {
        this.habit = contacts
        notifyDataSetChanged()
    }
}

// TODO ? 머야 리사이클러뷰에는 추가되고 room에는 추가가 안되는 건가? 나중에 알아보기!