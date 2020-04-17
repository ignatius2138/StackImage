package com.luck.ignatius.stockimageshopping.detailesscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luck.ignatius.stockimageshopping.database.CartTable
import com.luck.ignatius.stockimageshopping.database.CartTableDao
import com.luck.ignatius.stockimageshopping.network.Data
import kotlinx.coroutines.*

class DetailsScreenViewModel(val data: Data, app: Application, private val dataSource: CartTableDao): AndroidViewModel(app){
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _selectedData = MutableLiveData<Data>()
    val selectedData: LiveData<Data>
        get() = _selectedData

    init {
        _selectedData.value = data
    }

    fun addItemToCart() {
        uiScope.launch {
            val newCartItem = CartTable(url = data.assets.small_thumb.url, description = data.description, price = data.id.toInt()/10000000)
            insert(newCartItem)
        }
    }

    private suspend fun insert(newCartItem: CartTable){
        withContext(Dispatchers.IO) {
            dataSource.insert(newCartItem)
            Log.i("insert", newCartItem.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}