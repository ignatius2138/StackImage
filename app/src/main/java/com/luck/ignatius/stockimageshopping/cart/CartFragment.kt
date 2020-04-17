package com.luck.ignatius.stockimageshopping.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luck.ignatius.stockimageshopping.R
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.databinding.FragmentCartBinding

class CartFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = StackImageDatabase.getInstance(application).cartTableDao
        val viewModelFactory = CartViewModelFactory(dataSource, application)
        val cartViewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
        binding.viewModel = cartViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}