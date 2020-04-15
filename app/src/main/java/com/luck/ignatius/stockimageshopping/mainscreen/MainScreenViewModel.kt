package com.luck.ignatius.stockimageshopping.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luck.ignatius.stockimageshopping.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class StackPhotoApiStatus {LOADING, ERROR, DONE}

class MainScreenViewModel: ViewModel(){
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _status = MutableLiveData<StackPhotoApiStatus>()
    val status: LiveData<StackPhotoApiStatus>
        get() = _status

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _images = MutableLiveData<List<Data>>()
    val images: LiveData<List<Data>>
        get() = _images

    init {
        getImages()
    }

    private fun getImages() {
        coroutineScope.launch {
            val getImagesDeferred = StackPhotoApi.retrofitService.getImages()
            try {
                val result = getImagesDeferred.await()
                if (result.data.isNotEmpty()) {
                    _images.value = result.data
                    Log.i("url", result.data[0].assets.preview_1000.url)
                }
            }catch (t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}