package com.luck.ignatius.stockimageshopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.luck.ignatius.stockimageshopping.databinding.FragmentMainScreenBinding

class MainScreenFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        return binding.root
    }
}