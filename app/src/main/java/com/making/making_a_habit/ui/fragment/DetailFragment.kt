package com.making.making_a_habit.ui.fragment

import android.graphics.Color
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentDetailBinding
import com.example.domain.model.DetailData
import com.example.domain.model.DetailItem
import com.example.data.room.entity.HabitEntity
import com.example.domain.model.HabitData
import com.making.making_a_habit.ui.adapter.DetailAdapter
import com.making.making_a_habit.ui.dialog.DeleteDialog
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate, "DetailFragment") {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val viewModel : DetailViewModel by viewModels()
    private val adapter : DetailAdapter by lazy{ DetailAdapter(getAdapterData()) }
    private val deleteDialog : DeleteDialog by lazy { DeleteDialog(viewModel) }
    private lateinit var habitData : DetailData
    private val todayDate: LocalDate = LocalDate.now()

    override fun initData() {
        habitData = mainViewModel.selectedData.value!!
    }

    override fun initUi() {
        var habitDateStart : String

        with(binding){
            habitNameTextDetailshabitpage.text = habitData.name
            when(habitData.color){
                "red" -> habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                "yellow" -> habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                "green" -> habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1E6E6"))
                "blue" -> habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                "gray" -> habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AAAAAA"))
            }
            if(habitData.period == "기간"){
                Log.e(name, "initUi: 기간이 넘어옴!", )
                habitDateStart = habitData.dateStart + " ~ " + habitData.dateEnd
                habitDateTextDetailshabitpage.text = habitDateStart
            }
            else if(habitData.period == "횟수"){
                Log.e(name, "initUi: 횟수가 넘어옴!", )
                habitDateStart = habitData.dateStart + " ~"
                habitDateTextDetailshabitpage.text = habitDateStart
            }

            if (habitData.periodNum == 3 || habitData.periodNum == 15 || habitData.periodNum == 30) {
                adapter.sethabitPeriod(
                    DetailItem(
                        habitData.periodNum,
                        habitData.color,
                        habitData.period,
                        habitData.dateIng,
                        habitData.dateEnd,
                        habitData.roundFull,
                        habitData.lastRoundFull
                    )
                )
                setRecyclerView(habitData.periodNum)
            }

        }


    }

    override fun initListener() {
        with(binding){

            backBtnDetailshabitpage.setOnClickListener {
                findNavController().popBackStack()
            }

            deleteBtnDetailshabitpage.setOnClickListener {
                deleteDailog()
            }

        }
    }

    override fun initObserver() {

        mainViewModel.selectedData.observe(viewLifecycleOwner){

            /** 완료 이벤트 관련 **/
            if(it?.period == "횟수"){
                if(it.lastRoundFull == it.periodNum){
                    // TODO 완료 Dialog 띄우기
                }
            }
            else if(it?.period == "기간"){
                if(it.dateEnd == todayDate.toString()) {
                    // TODO 완료 Dialog 띄우기
                }
                else if(it.lastRoundFull == it.periodNum){
                    // TODO 완료 Dialog 띄우기
                }
                /*
                // TODO java.lang.NumberFormatException: For input string: "" 오류나는데 해결하기
                //기간일 경우  habitDateEnd을 넘겼을 경우 완료 이벤트
                dateEnd = dateEnd.replace("-", "")
                val dateToday = todayDate.toString().replace("-", "")
                if(dateEnd.toInt() < dateToday.toInt()){
                    // 완료 Dialog 띄우기
                }
                 */

            }
        }

    }

    inner class getAdapterData{
        fun updateData(dateIng: String, roundfull: Int, lastround: Int){
            viewModel.updateData(
                HabitData(
                    id = habitData.id,
                    name = habitData.name,
                    period = habitData.period,
                    periodNum = habitData.periodNum,
                    color = habitData.color,
                    dateStart = habitData.dateStart,
                    dateIng = dateIng,
                    dateEnd = habitData.dateEnd,
                    roundFull = roundfull,
                    lastRoundFull = lastround,
                    complete = habitData.complete,
                    comment = habitData.comment
                )
            )
        }
    }

    private fun setRecyclerView(periodNum: Int?) {
        binding.detailshabitpageRecyclerView.adapter = adapter
        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
        val spanCount = if (periodNum == 3) 3 else 5
        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
    }

    private fun deleteDailog(){
        deleteDialog.show(parentFragmentManager, "deleteDailog")
    }

    /*
    /***** 완료 이벤트 *****/
        fun completeDialog(){
            println("completeDialog가 실행됨")
            var habit : Habit
            if(intent.hasExtra("data")){
                println("completeDialog : hasExtra 잘 됨")
                CoroutineScope(Dispatchers.IO).launch {
                    habit = detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0))

                    if(habit.habitPeriod == "횟수"){
                        println("completeDialog : 횟수인 거 잘 인식 함")
                        if(habit.habitPeriodNum == 3)
                            showCompleteDialog()
                        else if(habit.habitPeriodNum == 15)
                            showCompleteDialog()
                        else if(habit.habitPeriodNum == 30)
                            showCompleteDialog()
                    }
                    if(habit.habitPeriod == "기간") showCompleteDialog()
                }
            }
        }

    /***** 완료 Dialog 띄우기 *****/
        fun showCompleteDialog(){
            val completedialog = CompleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            println("habitId : " + habitId)
            val bundle = Bundle()
            bundle.putInt("completeId", habitId)
            completedialog.arguments = bundle
            println("보냄")
            completedialog.show(supportFragmentManager, "deleteDialog")
        }
     */

}