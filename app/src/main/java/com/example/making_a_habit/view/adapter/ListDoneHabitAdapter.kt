package com.example.making_a_habit.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.databinding.ItemMainBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.DetailDoneHabitActivity
import com.example.making_a_habit.view.DetailHabitActivity
import com.example.making_a_habit.view.dialog.deleteDialogFragment

class ListDoneHabitAdapter(val mainItemClick: (Habit) -> Unit)
    : RecyclerView.Adapter<ListDoneHabitAdapter.ViewHolder>() {

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

        private val context = binding.root.context

        fun bind(habit: Habit) {
            binding.habitNameTextItemmain.text = habit.habitName
            binding.habitDateStartTextItemmain.text = habit.habitDateStart
            binding.habitRoundFullTextItemmain.text = habit.habitRoundFull.toString()

            itemView.setOnClickListener {
                mainItemClick(habit)

                val intent = Intent(context, DetailDoneHabitActivity::class.java)
                intent.putExtra("data", habit.habitId);
                intent.run { context.startActivity(this) }

                val fragmentDeleteDialog : deleteDialogFragment = deleteDialogFragment()
                val bundle : Bundle = Bundle()
                if (habit.habitId != null) {
                    bundle.putInt("deleteHabitId", habit.habitId)
                }
                fragmentDeleteDialog.arguments = bundle
                //Activity -> Fragement로 id 옮기기 bundle하면 가능하다네~~~~

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