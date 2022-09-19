package com.example.making_a_habit.view.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.making_a_habit.databinding.DeleteDialogFragmentBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.MainActivity
import com.example.making_a_habit.viewmodel.DeletedialogViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class deleteDialogFragment : DialogFragment() {

    val deletedialogViewModel: DeletedialogViewModel by activityViewModels()
    private lateinit var binding : DeleteDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var habit : Habit
        binding = DeleteDialogFragmentBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.completeBtnDialog.setOnClickListener{

            /** DetailHabitActivity에 habitId 받은 후 delete 하기 **/
            val deleteHabitId = arguments?.getInt("deleteId")
            println("deleteHabitId : " + deleteHabitId)
            CoroutineScope(Dispatchers.IO).launch{
                habit = deletedialogViewModel.getHabitId(arguments?.getInt("deleteId")!!)
                deletedialogViewModel.delete(Habit(deleteHabitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, habit.habitDateIng, habit.habitDateEnd, habit.habitRoundFull, 0, habit.habitComplete, habit.habitComment))
            }

            activity?.let{
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            dismiss()
            requireActivity().finish()
        }
        binding.cancelBtnDialog.setOnClickListener{
            dismiss()
        }
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(true)

        return binding.root
    }
}