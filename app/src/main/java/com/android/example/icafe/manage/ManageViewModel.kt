package com.android.example.icafe.manage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManageViewModel: ViewModel() {

    private val _eventClickSubmit = MutableLiveData<Boolean>()
    val eventClickSubmit : LiveData<Boolean>
        get() = _eventClickSubmit

    private val _eventChangeColor = MutableLiveData<Int>()
    val eventChangeColor : LiveData<Int>
        get() = _eventChangeColor

    private val _safeArgs = MutableLiveData<Int>()
    val safeArgs : LiveData<Int>
        get() = _safeArgs

    private val _toastPleaseSelect = MutableLiveData<Boolean>()
    val toastPleaseSelect : LiveData<Boolean>
        get() = _toastPleaseSelect

    private var selected :Int = 100;
    private var selectBoolean : Boolean = false;

    init{
        _eventChangeColor.value =  0
        _eventClickSubmit.value = false
        selectBoolean = false
    }



    fun selectedClick(num : Int){
//        Log.i("Manage","True : ${num}")
        _eventChangeColor.value = num
        _safeArgs.value = num
        selected = num
        selectBoolean = true
    }


    fun clickSubmit(){
        if(selectBoolean){
            _eventClickSubmit.value = true
        }else{
            _toastPleaseSelect.value = true
        }

    }

}