package com.making.making_a_habit.ui.fragment

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentCommentBinding
import com.example.domain.model.DetailData
import com.example.data.entity.HabitEntity
import com.example.domain.model.HabitData
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.CommentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentFragment : BaseFragment<FragmentCommentBinding>(FragmentCommentBinding::inflate, "CommentFragment") {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val viewModel: CommentViewModel by viewModels()
    private lateinit var habitData : DetailData

    override fun initData() {
        habitData = mainViewModel.selectedData.value!!
    }

    override fun initUi() {
        with(binding){

            creatingBtnCreatingcommentspage.text = habitData.name
            when(habitData.color){
                "red" -> habitNameTextCreatingcommentspage.setTextColor(Color.parseColor("#FFAEAE"))
                "yellow" -> habitNameTextCreatingcommentspage.setTextColor(Color.parseColor("#FFE8AE"))
                "green" -> habitNameTextCreatingcommentspage.setTextColor(Color.parseColor("#B1E6E6"))
                "blue" -> habitNameTextCreatingcommentspage.setTextColor(Color.parseColor("#AED8FF"))
                "gray" -> habitNameTextCreatingcommentspage.setTextColor(Color.parseColor("#AAAAAA"))
            }

        }
    }

    override fun initListener() {
        with(binding){

            backBtnCreatingcommentspage.setOnClickListener {
                findNavController().popBackStack()
            }

            cancelBtnCreatingcommentspage.setOnClickListener {
                findNavController().popBackStack()
                findNavController().popBackStack()
            }

            habitComemntEdittextCreatingcommentspage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {} // 텍스트가 변경된 이후에 동작

                override fun onTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {} // 텍스트가 변경되기 바로 이전에 동작

                override fun afterTextChanged(h: Editable?) {
                    if(h != null && !h.toString().equals("")){
                        if(h.toString().length == 100){
                            showShortToast("커멘트는 100자까지만 쓸 수 있습니다!")
                        }
                    }
                } // 텍스트가 변경되는 동시에 동작
            })

            creatingBtnCreatingcommentspage.setOnClickListener {
                val comment = habitComemntEdittextCreatingcommentspage.text.toString()
                val commentNo = "이 습관은 커멘트가 작성되지 않았습니다."
                if(comment == ""){
                    viewModel.updateData(
                        HabitData(
                            id = habitData.id,
                            name = habitData.name,
                            period = habitData.period,
                            periodNum = habitData.periodNum,
                            color = habitData.color,
                            dateStart = habitData.dateStart,
                            dateIng = habitData.dateIng,
                            dateEnd = habitData.dateEnd,
                            roundFull = habitData.roundFull,
                            lastRoundFull = habitData.lastRoundFull,
                            complete = habitData.complete,
                            comment = commentNo
                        )
                    )
                }
                else{
                    viewModel.updateData(
                        HabitData(
                            id = habitData.id,
                            name = habitData.name,
                            period = habitData.period,
                            periodNum = habitData.periodNum,
                            color = habitData.color,
                            dateStart = habitData.dateStart,
                            dateIng = habitData.dateIng,
                            dateEnd = habitData.dateEnd,
                            roundFull = habitData.roundFull,
                            lastRoundFull = habitData.lastRoundFull,
                            complete = habitData.complete,
                            comment = comment
                        )
                    )
                }


                findNavController().popBackStack()
                findNavController().popBackStack()
            }

        }
    }

    override fun initObserver() {
    }

}