package com.making.making_a_habit.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate:Inflate<VB>,
    name: String
) : Fragment() {

    val name = name

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(name, "Fragment onAttach", )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(name, "Fragment onCreate", )
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(name, "Fragment onCreateView", )
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(name, "Fragment onViewCreated", )
        initUi()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(name, "Fragment onViewStateRestored", )
    }

    override fun onStart() {
        super.onStart()
        Log.e(name, "Fragment onStart", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(name, "Fragment onResume", )
        initListener()
        initObserver()
    }

    override fun onPause() {
        super.onPause()
        Log.e(name, "Fragment onPause", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(name, "Fragment onStop", )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(name, "Fragment onSaveInstanceState", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(name, "Fragment onDestroyView", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(name, "Fragment onDestroy", )
    }

    abstract fun initData()
    abstract fun initUi()
    abstract fun initListener()
    abstract fun initObserver()

    fun showShortToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    fun showLongToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_LONG).show()
        }
    }
}