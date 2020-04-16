package com.luck.ignatius.stockimageshopping.detailesscreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luck.ignatius.stockimageshopping.network.Data
import java.lang.IllegalArgumentException

class DetailsScreenViewModelFactory(private val data: Data, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(DetailsScreenViewModel::class.java)) {
            return DetailsScreenViewModel(data, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}