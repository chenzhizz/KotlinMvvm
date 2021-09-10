package com.sumansoul.kotlinDemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CoroutineViewModel :ViewModel() {

    var netData:LiveData<String> = liveData {
        var data=getNetData()
        emit(data)
    }
    suspend fun getNetData()= withContext(Dispatchers.IO){
        delay(5000)
        "{\"data\":\"测试\"}"
    }
}