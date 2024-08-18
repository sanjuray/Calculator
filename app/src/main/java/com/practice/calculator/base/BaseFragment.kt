package com.practice.calculator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.practice.calculator.errorLogs


abstract class BaseFragment<VB: ViewBinding>(
    private val layoutId: Int,
): Fragment() {

    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            binding = DataBindingUtil.inflate(
                inflater,
                layoutId, container, false
            )
        }catch (e: Exception){
            e.errorLogs()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            init()
        }catch (e: Exception){
            e.errorLogs()
        }
    }

    private fun init(){
        initUI()
        initOnClickListeners()
    }

    abstract fun initUI()

    abstract fun initOnClickListeners()

}

