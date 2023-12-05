package com.making.making_a_habit.ui.fragment

import android.util.Log
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentHomeBinding
import com.example.domain.model.DetailData
import com.example.data.entity.HabitEntity
import com.making.making_a_habit.ui.adapter.MainRecyclerViewAdapter
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.HomeViewModel
// TODO Detail에서 체크시 Room데이터 Update는 되지만, 앱에서는 안되고 안보임. 이거 해결하기
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate, "MainFragment") {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val viewModel : HomeViewModel by viewModels()
    private val adapter : MainRecyclerViewAdapter by lazy{ MainRecyclerViewAdapter() }
    private lateinit var data : HabitEntity

    override fun initData() {
        viewModel.getAllData()
        /* 앱 최초 실행 확인
        val sharedPreference =  getSharedPreferences("isFirst",Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val first = sharedPreference.getBoolean("isFirst", false)
        if(first == false){
            setPushNotification()
            Log.e("Is first Time?", "first")
            editor.putBoolean("isFirst", true)
            editor.apply()
        }
        else{
            Log.e("Is first Time?", "not first")
        }
        */
    }

    override fun initUi() {
        initMainRecycler()
    }

    override fun initListener() {
        with(binding){

            creatingPageBtn.setOnClickListener {
                if(mainRecyclerView.size == 3){
                    showShortToast("진행 습관은 3개만 가능합니다!")
                    Log.e(name, "initListener: 진행 습관은 3개만 가능합니다!", )
                }
                else if(mainRecyclerView.size < 3){
                    // 습관 생성 페이지로 이동
                    Log.e(name, "initListener: mainRecyclerView.size < 3", )
                    findNavController().navigate(R.id.action_mainFragment_to_createHabitFragment)
                }
            }

            setupPageBtn.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_setUpFragment)
            }

            historyPageBtn.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
            }

            adapter.onMainItemClick={
                mainViewModel.changeSelectedData(
                    DetailData(
                        id = it.habitId,
                        name = it.habitName,
                        period = it.habitPeriod,
                        periodNum = it.habitPeriodNum,
                        color = it.habitColor,
                        dateStart = it.habitDateStart,
                        dateIng = it.habitDateIng,
                        dateEnd = it.habitDateEnd,
                        roundFull = it.habitRoundFull,
                        lastRoundFull = it.habitLastRoundFull,
                        complete = it.habitComplete,
                        comment = it.habitComment
                    )
                )
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
            }

        }
    }

    override fun initObserver() {
        viewModel.habitData.observe(viewLifecycleOwner){
            adapter.habitList = it.toMutableList()
            adapter.sethabit(it)
        }
    }

    private fun initMainRecycler(){
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.setHasFixedSize(true)
    }

}