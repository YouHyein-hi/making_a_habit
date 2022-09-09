package com.example.making_a_habit.view.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.CompleteDialogFragmentBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.CommentActivity
import com.example.making_a_habit.view.ListDoneHabitActivity
import com.example.making_a_habit.viewmodel.CompleteDialogViewModel
import com.example.making_a_habit.viewmodel.DeletedialogViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CompleteDialogFragment : DialogFragment() {
    private var param1: String? = null
    private var param2: String? = null

    val completeDialogViewModel: CompleteDialogViewModel by activityViewModels()
    private lateinit var binding : CompleteDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var habit: Habit
        var completeText: String
        binding = CompleteDialogFragmentBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        CoroutineScope(Dispatchers.IO).launch{
            val NumberDateEnd: LocalDate = LocalDate.now()

            habit = completeDialogViewModel.getHabitId(arguments?.getInt("completeId")!!)
            getActivity()?.runOnUiThread(){
                completeText = habit.habitPeriodNum.toString() + "칸 중 " + habit.habitRoundFull + "칸을 완료하셨어요!"

                binding.textDialog.text = completeText
            }

            binding.completeBtnDialog.setOnClickListener{
                /** DetailHabitActivity에 habitId 받은 후 update 하기 **/
                val habitId = arguments?.getInt("completeId")
                CoroutineScope(Dispatchers.IO).launch{
                    if(habit.habitPeriod == "횟수"){
                        completeDialogViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, NumberDateEnd.toString(), habit.habitRoundFull, habit.habitLastRoundFull, true, habit.habitComment))
                    }
                    else {
                        completeDialogViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull, true, habit.habitComment))
                    }

                }

                dismiss()
                requireActivity().finish()

                val intent = Intent(getActivity(), CommentActivity::class.java)
                intent.putExtra("commentId", habitId)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
            binding.cancelBtnDialog.setOnClickListener{
                val habitId = arguments?.getInt("completeId")
                val completecommend = "이 습관은 커멘트가 작성되지 않았습니다."
                CoroutineScope(Dispatchers.IO).launch{
                    if(habit.habitPeriod == "횟수"){
                        completeDialogViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, NumberDateEnd.toString(), habit.habitRoundFull, habit.habitLastRoundFull, true, completecommend))
                    }
                    else {
                        completeDialogViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull, true, completecommend))
                    }
                }
                dismiss()
                requireActivity().finish()
            }
        }

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CompleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}