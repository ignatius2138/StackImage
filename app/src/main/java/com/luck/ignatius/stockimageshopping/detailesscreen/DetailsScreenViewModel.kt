package com.luck.ignatius.stockimageshopping.detailesscreen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
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
    private val sharedPreferences = app.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)!!

    private val _selectedData = MutableLiveData<Data>()
    val selectedData: LiveData<Data>
        get() = _selectedData

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    init {
        _selectedData.value = data
    }

    fun addItemToCart() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (dataSource.checkIfImageAlreadyExists(data.assets.huge_thumb.url, sharedPreferences.getString("accountName", "not logged in")!!) == 0) {
                    val newCartItem = CartTable(url = data.assets.huge_thumb.url, description = data.description, price = data.id.toInt()/100000000, accountName = sharedPreferences.getString("accountName", "not logged in")!!)
                    insert(newCartItem)
                    withContext(Dispatchers.Main) {
                        _showSnackbarEvent.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(getApplication(), "Image already in a cart or bought", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private suspend fun insert(newCartItem: CartTable){
        withContext(Dispatchers.IO) {
            dataSource.insert(newCartItem)
            Log.i("insert", newCartItem.toString())
        }
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}