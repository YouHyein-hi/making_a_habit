package com.making.making_a_habit.ui.fragment


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentCreateBinding
import com.example.data.entity.HabitEntity
import com.example.domain.model.HabitData
import com.making.making_a_habit.viewmodel.fragmentViewModel.CreateViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class CreateFragment : BaseFragment<FragmentCreateBinding>(FragmentCreateBinding::inflate, "CreateFragment") {

    private val viewModel: CreateViewModel by viewModels()
    private lateinit var callback: OnBackPressedCallback
    private var id: Int? = null
    private var habitPeriod: String? = null
    private var hebitPeriodNum: Int? = 0
    private var habitColor: String? = null

    override fun initData() {
    }

    override fun initUi() {
    }

    override fun initListener() {
        with(binding){

            backBtnCreatinghabitpage.setOnClickListener {
                findNavController().popBackStack()
            }

            habitNameEdittext.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} // 텍스트가 변경된 이후에 동작

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} // 텍스트가 변경되기 바로 이전에 동작

                override fun afterTextChanged(h: Editable?) {
                    if(h != null && !h.toString().equals("")){
                        if(h.toString().length == 20){
                            showShortToast("습관 이름은 20자까지만 가능합니다!")
                        }
                    }
                } // 텍스트가 변경되는 동시에 동작
            })

            /***** habitPeriod_btn 클릭하면 버튼눌림효과 유지 *****/
            // 기간 눌림효과, 눌림효과 해제  횟수 눌림효과, 눌림효과 해제
            // 버튼 누른 상태에서 다른 버튼 누르면 눌림효과 해제 후 눌림
            // TODO BindingAdapter로 변경하기 ㄹㅇ 미친 개더러운 코드네
            // 기간, 횟수 버튼 로직
            habitPeriodBtnTime.setOnClickListener {
                setPeriodButton(habitPeriodBtnTime, listOf(habitPeriodBtnNumber), "기간")
            }
            habitPeriodBtnNumber.setOnClickListener {
                setPeriodButton(habitPeriodBtnNumber, listOf(habitPeriodBtnTime), "횟수")
            }
            // 기간 수 Button 로직
            habitPeriodNumBtn3.setOnClickListener {
                setPeriodNumButton(habitPeriodNumBtn3, listOf(habitPeriodNumBtn15, habitPeriodNumBtn30), 3)
            }
            habitPeriodNumBtn15.setOnClickListener {
                setPeriodNumButton(habitPeriodNumBtn15, listOf(habitPeriodNumBtn3, habitPeriodNumBtn30), 15)
            }
            habitPeriodNumBtn30.setOnClickListener {
                setPeriodNumButton(habitPeriodNumBtn30, listOf(habitPeriodNumBtn3, habitPeriodNumBtn15), 30)
            }
            // 테마 버튼 로직
            themeRed.setOnClickListener {
                setThemeButton(themeRed, listOf(themeYellow, themeGreen, themeBlue, themeGray), "red")
            }
            themeYellow.setOnClickListener {
                setThemeButton(themeYellow, listOf(themeRed, themeGreen, themeBlue, themeGray), "yellow")
            }
            themeGreen.setOnClickListener {
                setThemeButton(themeGreen, listOf(themeRed, themeYellow, themeBlue, themeGray), "green")
            }
            themeBlue.setOnClickListener {
                setThemeButton(themeBlue, listOf(themeRed, themeYellow, themeGreen, themeGray), "blue")
            }
            themeGray.setOnClickListener {
                setThemeButton(themeGray, listOf(themeRed, themeYellow, themeGreen, themeBlue), "gray")
            }


            /***** '생성하기'버튼 클릭시 DB 저장 및 화면 전환 *****/
            // TODO 코드 가독성 있게 변경하기
            creatinghabitBtn.setOnClickListener{
                val habitName = habitNameEdittext.text.toString()
                val habitDateStart: LocalDate = LocalDate.now()
                var habitDateEnd = ""

                habitDateEnd = when {
                    habitPeriodNumBtn3.isSelected -> habitDateStart.plusDays(2).toString()
                    habitPeriodNumBtn15.isSelected -> habitDateStart.plusDays(14).toString()
                    habitPeriodNumBtn30.isSelected -> habitDateStart.plusDays(29).toString()
                    else -> ""
                }

                val isHabitNameNotEmpty = habitName.isNotEmpty()
                val isPeriodSelected = habitPeriodBtnTime.isSelected || habitPeriodBtnNumber.isSelected
                val isPeriodNumSelected = habitPeriodNumBtn3.isSelected || habitPeriodNumBtn15.isSelected || habitPeriodNumBtn30.isSelected
                val isThemeSelected = themeRed.isSelected || themeYellow.isSelected || themeGreen.isSelected || themeBlue.isSelected || themeGray.isSelected


                if (isHabitNameNotEmpty && isPeriodSelected && isPeriodNumSelected && isThemeSelected) {
                    viewModel.insertData(
                        HabitData(
                            id =  id,
                            name = habitName,
                            period = habitPeriod.toString(),
                            periodNum = hebitPeriodNum,
                            color = habitColor.toString(),
                            dateStart = habitDateStart.toString(),
                            dateIng = habitDateStart.toString(),
                            dateEnd = habitDateEnd,
                            roundFull = 0,
                            lastRoundFull = 0,
                            complete = false,
                            comment = "임시"
                        )
                    )

                    findNavController().navigate(R.id.action_createHabitFragment_to_mainFragment)
                }
                else{
                    showShortToast("모든 옵션을 선택해주세요.")
                    Log.e(name, "initListener: 모든 옵션을 선택해주세요.", )
                }
            }



        }
    }

    override fun initObserver() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setPeriodButton(selectedButton: View, otherButton: List<View>, periodType: String) {
        if (selectedButton.isSelected) return
        selectedButton.isSelected = true
        otherButton.forEach { it.isSelected = false }
        habitPeriod = periodType
    }

    private fun setPeriodNumButton(selectedButton: View, otherButton: List<View>, periodNum: Int) {
        if (selectedButton.isSelected) return

        selectedButton.isSelected = true
        otherButton.forEach { it.isSelected = false }
        hebitPeriodNum = periodNum
    }

    private fun setThemeButton(selectedButton: View, otherButton: List<View>, color: String) {
        if (selectedButton.isSelected) return

        selectedButton.isSelected = true
        otherButton.forEach { it.isSelected = false }
        habitColor = color
    }

}