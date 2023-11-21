package com.making.making_a_habit.view.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentHistoryBinding
import com.making.making_a_habit.view.adapter.ListDoneHabitAdapter

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate, "HistoryFragment") {

    private val adapter : ListDoneHabitAdapter by lazy{ ListDoneHabitAdapter() }

    override fun initData() {
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
    }

    /*
    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(listDoneHabitViewModel.getAll().sortedWith(compareBy { it.habitDateEnd }))
        }
     }
    */

    private fun initMainRecycler(){
        binding.listdonehabitRecyclerView.adapter = adapter
        binding.listdonehabitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listdonehabitRecyclerView.setHasFixedSize(true)
    }

}