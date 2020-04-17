package com.luck.ignatius.stockimageshopping.detailesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.databinding.FragmentDetailedScreenBinding

class DetailsScreenFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailedScreenBinding.inflate(inflater)
        val data = DetailsScreenFragmentArgs.fromBundle(arguments!!).selectedImage
        val dataSource = StackImageDatabase.getInstance(application).cartTableDao
        val viewModelFactory = DetailsScreenViewModelFactory(data, application, dataSource)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsScreenViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }
}