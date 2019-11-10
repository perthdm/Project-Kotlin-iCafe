package com.android.example.icafe.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {

    private val _eventClickHistory = MutableLiveData<Boolean>()
    val eventClickHistory : LiveData<Boolean>
        get() = _eventClickHistory

    private val _eventClickManager = MutableLiveData<Boolean>()
    val eventClickManager : LiveData<Boolean>
        get() = _eventClickManager

    private val _eventClickAbout = MutableLiveData<Boolean>()
    val eventClickAbout : LiveData<Boolean>
        get() = _eventClickAbout


    init{
        _eventClickHistory.value = false
        _eventClickManager.value = false
        _eventClickAbout.value = false

    }

    fun clickManager(){
        Log.i("MenuViewModel","Clicked --> Manager Function")
        _eventClickManager.value = true
    }

    fun clickHistory(){
        Log.i("MenuViewModel","Clicked --> History Function")
        _eventClickHistory.value = true
    }

    fun clickAbout(){
        Log.i("MenuViewModel","Clicked --> About Function")
        _eventClickAbout.value = true
    }






}