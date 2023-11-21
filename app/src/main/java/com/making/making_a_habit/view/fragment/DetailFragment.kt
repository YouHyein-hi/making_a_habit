package com.making.making_a_habit.view.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentDetailBinding
import com.making.making_a_habit.view.adapter.DetailHabitAdapter
import com.making.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.making.making_a_habit.viewmodel.DetailhabitViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.MainViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate, "DetailFragment") {

    private val viewModel : DetailhabitViewModel by viewModels()
    //private val adapter : DetailHabitAdapter by lazy{ DetailHabitAdapter() }

    override fun initData() {
    }

    override fun initUi() {
    }

    override fun initListener() {
        with(binding){

            backBtnDetailshabitpage.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    override fun initObserver() {
    }

}