package com.example.making_a_habit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.model.Habit

class MainAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var habit: List<Habit> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habit.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(habit[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val habitName = itemView.findViewById<TextView>(R.id.habitName_text_itemmain)
        private val habitDateStart = itemView.findViewById<TextView>(R.id.habitDateStart_text_itemmain)

        fun bind(habit: Habit) {
            habitName.text = habit.habitName
            habitDateStart.text = habit.habitDateStart

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