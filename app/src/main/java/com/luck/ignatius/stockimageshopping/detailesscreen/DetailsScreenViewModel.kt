package com.luck.ignatius.stockimageshopping.detailesscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luck.ignatius.stockimageshopping.network.Data
import com.luck.ignatius.stockimageshopping.network.Image
import com.luck.ignatius.stockimageshopping.network.Preview

class DetailsScreenViewModel(data: Data, app: Application): AndroidViewModel(app){
    private val _selectedData = MutableLiveData<Data>()
    val selectedData: LiveData<Data>
        get() = _selectedData

    init {
        _selectedData.value = data
    }
}