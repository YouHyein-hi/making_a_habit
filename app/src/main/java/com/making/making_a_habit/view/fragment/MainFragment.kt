package com.making.making_a_habit.view.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.R
import com.making.making_a_habit.base.BaseFragment
import com.making.making_a_habit.databinding.FragmentMainBinding
import com.making.making_a_habit.view.CreatinghabitActivity
import com.making.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.making.making_a_habit.viewmodel.fragmentViewModel.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate, "MainFragment") {

    private val viewModel : MainViewModel by viewModels()
    private val adapter : MainRecyclerViewAdapter by lazy{ MainRecyclerViewAdapter() }

    override fun initData() {
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
                // TODO ViewModel 연결하면 이 코드 지우기
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

        }
    }

    override fun initObserver() {
    }

    private fun initMainRecycler(){
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.setHasFixedSize(true)
    }

}