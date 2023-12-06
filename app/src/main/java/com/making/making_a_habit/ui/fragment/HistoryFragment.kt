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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        id = it.id,
                        name = it.name,
                        period = it.period,
                        periodNum = it.periodNum,
                        color = it.color,
                        dateStart = it.dateStart,
                        dateIng = it.dateIng,
                        dateEnd = it.dateEnd,
                        roundFull = it.roundFull,
                        lastRoundFull = it.roundFull,
                        complete = it.complete,
                        comment = it.comment
                    )
                )
                findNavController().navigate(R.id.action_historyFragment_to_historyDetailFragment)
            }

        }
    }

    override fun initObserver() {
        viewModel.habitData.observe(viewLifecycleOwner){
            if (it != null) {
                adapter.habitList = it.toMutableList()
                adapter.sethabit(it)
            }
        }
    }

    private fun initMainRecycler(){
        binding.listdonehabitRecyclerView.adapter = adapter
        binding.listdonehabitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listdonehabitRecyclerView.setHasFixedSize(true)
    }

}