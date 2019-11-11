package com.android.example.icafe.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {

    private val _eventInit = MutableLiveData<Boolean>()
    val  eventInit : LiveData<Boolean>
        get() = _eventInit

    private val _eventSubmitData = MutableLiveData<Boolean>()
    val  eventSubmitData : LiveData<Boolean>
        get() = _eventSubmitData

    private val _eventGetInput = MutableLiveData<Boolean>()
    val  eventGetInput : LiveData<Boolean>
        get() = _eventGetInput

    val _toastEmptyInput= MutableLiveData<Boolean>()
    val toastEmptyInput  : LiveData<Boolean>
        get() = _toastEmptyInput

    private val _timeLiveData = MutableLiveData<Long>()
    val timeLiveData  : LiveData<Long>
        get() = _timeLiveData

    init {
        _eventInit.value = true
        _eventSubmitData.value = false
    }

    fun submitEnter(){
        _eventGetInput.value = true
    }

    fun checkInput(name:String , time:String){


        if(name == ""  || time == ""){
            _toastEmptyInput.value = true
        }else{
            var timeLong = time.toLong()

            Log.i("DetailViewModel","User: ${name} || Time: ${time} Sec.")
            _timeLiveData.value = timeLong
            _eventSubmitData.value = true
        }
    }
}