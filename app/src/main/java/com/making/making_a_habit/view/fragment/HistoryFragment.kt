package com.making.making_a_habit.view.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentHistoryBinding
import com.making.making_a_habit.view.adapter.HistoryAdapter
import com.making.making_a_habit.viewmodel.fragmentViewModel.HistoryViewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate, "HistoryFragment") {

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