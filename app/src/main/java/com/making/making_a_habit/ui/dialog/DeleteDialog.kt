package com.making.making_a_habit.ui.dialog

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.DetailData
import com.making.making_a_habit.base.BaseDialog
import com.making.making_a_habit.databinding.DialogDeleteBinding
import com.making.making_a_habit.viewmodel.activityViewModel.MainViewModel
import com.making.making_a_habit.viewmodel.dialogViewModel.DeletedialogViewModel
import com.making.making_a_habit.viewmodel.fragmentViewModel.DetailViewModel

class DeleteDialog(
    private val viewModel : DetailViewModel
) : BaseDialog<DialogDeleteBinding>(DialogDeleteBinding::inflate, "DeleteDialog") {

    private val mainViewModel : MainViewModel by activityViewModels()
    val deletedialogViewModel: DeletedialogViewModel by viewModels()
    private lateinit var habitData : DetailData

    override fun initData() {
        if(mainViewModel.selectedData.value != null){
            habitData = mainViewModel.selectedData.value!!
        }
        else{
            showShortToast("데이터가 없습니다!")
            dismiss()
        }
    }

    override fun initUI() {
    }

    override fun initListener() {
        with(binding){
            cancelBtnDeleteDialog.setOnClickListener { dismiss() }

            okBtnDeleteDialog.setOnClickListener {
                habitData.id?.let { viewModel.deleteData(it) }
                findNavController().popBackStack()
                dismiss()
            }
        }
    }

    override fun initObserver() {
    }

}