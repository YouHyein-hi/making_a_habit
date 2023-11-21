package com.making.making_a_habit.view.fragment


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentCreateBinding
import com.making.making_a_habit.model.Habit
import java.time.LocalDate

class CreateFragment : BaseFragment<FragmentCreateBinding>(FragmentCreateBinding::inflate, "CreateFragment") {

    // val creatinghabitViewModel: CreatinghabitViewModel by viewModels()
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
            // TODO BindingAdapter로 변경하기
            // 기간 Button
            habitPeriodBtnTime.setOnClickListener{
                if(habitPeriodBtnTime.isSelected()){  // 버튼이 눌려있을 경우 (selected 상태)
                    habitPeriodBtnTime.isSelected = false
                } else { // 버튼이 안 눌려있을 경우 (selected 상태)
                    habitPeriodBtnTime.isSelected = true
                    habitPeriodBtnNumber.isSelected = false  // 다른 버튼이 눌린 상태에서 눌렸을 경우를 대비
                }
                habitPeriod = "기간"
            }
            // 횟수 Button
            habitPeriodBtnNumber.setOnClickListener{
                if(habitPeriodBtnNumber.isSelected()){  // 버튼이 눌려있을 경우 (selected 상태)
                    habitPeriodBtnNumber.isSelected = false
                } else { // 버튼이 안 눌려있을 경우 (selected 상태)
                    habitPeriodBtnNumber.isSelected = true
                    habitPeriodBtnTime.isSelected = false  // 다른 버튼이 눌린 상태에서 눌렸을 경우를 대비
                }
                habitPeriod = "횟수"
            }

            // 3칸 Button
            habitPeriodNumBtn3.setOnClickListener{
                if(habitPeriodNumBtn3.isSelected()){
                    habitPeriodNumBtn3.isSelected = false
                } else {
                    habitPeriodNumBtn3.isSelected = true
                    habitPeriodNumBtn15.isSelected = false
                    habitPeriodNumBtn30.isSelected = false
                }
                hebitPeriodNum = 3
            }
            // 15칸 button
            habitPeriodNumBtn15.setOnClickListener{
                if(habitPeriodNumBtn15.isSelected()){
                    habitPeriodNumBtn15.isSelected = false
                } else {
                    habitPeriodNumBtn15.isSelected = true
                    habitPeriodNumBtn3.isSelected = false
                    habitPeriodNumBtn30.isSelected = false
                }
                hebitPeriodNum = 15
            }
            // 30칸 button
            habitPeriodNumBtn30.setOnClickListener{
                if(habitPeriodNumBtn30.isSelected()){
                    habitPeriodNumBtn30.isSelected = false
                } else {
                    habitPeriodNumBtn30.isSelected = true
                    habitPeriodNumBtn3.isSelected = false
                    habitPeriodNumBtn15.isSelected = false
                }
                hebitPeriodNum = 30
            }

            // theme red button
            themeRed.setOnClickListener{
                if(themeRed.isSelected()){
                    themeRed.isSelected = false
                } else {
                    themeRed.isSelected = true
                    themeYellow.isSelected = false
                    themeGreen.isSelected = false
                    themeBlue.isSelected = false
                    themeGray.isSelected = false
                }
                habitColor = "red"
            }
            // theme yellow button
            themeYellow.setOnClickListener{
                if(themeYellow.isSelected()){
                    themeYellow.isSelected = false
                } else {
                    themeYellow.isSelected = true
                    themeRed.isSelected = false
                    themeGreen.isSelected = false
                    themeBlue.isSelected = false
                    themeGray.isSelected = false
                }
                habitColor = "yellow"
            }
            // theme green button
            themeGreen.setOnClickListener{
                if(themeGreen.isSelected()){
                    themeGreen.isSelected = false
                } else {
                    themeGreen.isSelected = true
                    themeRed.isSelected = false
                    themeYellow.isSelected = false
                    themeBlue.isSelected = false
                    themeGray.isSelected = false
                }
                habitColor = "green"
            }
            // theme blue button
            themeBlue.setOnClickListener{
                if(themeBlue.isSelected()){
                    themeBlue.isSelected = false
                } else {
                    themeBlue.isSelected = true
                    themeRed.isSelected = false
                    themeYellow.isSelected = false
                    themeGreen.isSelected = false
                    themeGray.isSelected = false
                }
                habitColor = "blue"
            }
            // theme gray button
            themeGray.setOnClickListener{
                if(themeGray.isSelected()){
                    themeGray.isSelected = false
                } else {
                    themeGray.isSelected = true
                    themeRed.isSelected = false
                    themeYellow.isSelected = false
                    themeGreen.isSelected = false
                    themeBlue.isSelected = false
                }
                habitColor = "gray"
            }

            /***** '생성하기'버튼 클릭시 DB 저장 및 화면 전환 *****/
            // TODO 코드 가독성 있게 변경하기
            creatinghabitBtn.setOnClickListener{
                val habitName = habitNameEdittext.text.toString()
                val habitDateStart: LocalDate = LocalDate.now()
                var habitDateEnd = ""

                if(habitPeriodNumBtn3.isSelected){
                    habitDateEnd = habitDateStart.plusDays(2).toString()
                }
                else if(habitPeriodNumBtn15.isSelected){
                    habitDateEnd = habitDateStart.plusDays(14).toString()
                }
                else if(habitPeriodNumBtn30.isSelected){
                    habitDateEnd = habitDateStart.plusDays(29).toString()
                }

                if( !habitNameEdittext.text.toString().trim().isEmpty() &&
                    (habitPeriodBtnTime.isSelected || habitPeriodBtnNumber.isSelected) &&
                    (habitPeriodNumBtn3.isSelected || habitPeriodNumBtn15.isSelected || habitPeriodNumBtn30.isSelected) &&
                    (themeRed.isSelected || themeYellow.isSelected || themeGreen.isSelected || themeBlue.isSelected || themeGray.isSelected)
                ){
                    val habit = Habit(id, habitName, habitPeriod.toString(), hebitPeriodNum,habitColor.toString(), habitDateStart.toString(), habitDateStart.toString(), habitDateEnd, 0, 0, false, "임시")
                    println("Habit : " + habit)

                    //creatinghabitViewModel.insert(habit)
                    println("다 선택함")

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


}