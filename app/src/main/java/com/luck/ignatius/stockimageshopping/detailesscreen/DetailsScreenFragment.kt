package com.luck.ignatius.stockimageshopping.detailesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luck.ignatius.stockimageshopping.databinding.FragmentDetailedScreenBinding

class DetailsScreenFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailedScreenBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val data = DetailsScreenFragmentArgs.fromBundle(arguments!!).selectedImage
        val viewModelFactory = DetailsScreenViewModelFactory(data, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsScreenViewModel::class.java)
        return binding.root
    }
}