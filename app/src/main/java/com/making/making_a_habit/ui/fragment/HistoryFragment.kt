package com.making.making_a_habit.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentHistoryBinding
import com.example.domain.model.DetailData
import com.making.making_a_habit.ui.adapter.HistoryAdapter
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.HistoryViewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate, "HistoryFragment") {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val viewModel : HistoryViewModel by viewModels()
    private val adapter : HistoryAdapter by lazy{ HistoryAdapter() }

    override fun initData() {
        viewModel.getAllData()
    }

    override fun initUi() {
        initMainRecycler()
    }

    override fun initListener() {
        with(binding){

            backBtnListdonehabitpage.setOnClickListener {
                findNavController().popBackStack()
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
                findNavController().navigate(R.id.action_historyFragment_to_historyDetailFragment)
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
        binding.listdonehabitRecyclerView.adapter = adapter
        binding.listdonehabitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listdonehabitRecyclerView.setHasFixedSize(true)
    }

}