package com.android.example.icafe.manage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.icafe.database.DataHistory
import com.android.example.icafe.database.DataHistoryDatabaseDao
import kotlinx.coroutines.*

class ManageViewModel(val database: DataHistoryDatabaseDao, application: Application): AndroidViewModel(application) {

    //==== Database ====//
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //==== Database ====//

    private val _eventClickSubmit = MutableLiveData<Boolean>()
    val eventClickSubmit : LiveData<Boolean>
        get() = _eventClickSubmit

    private val _eventChangeColor = MutableLiveData<Int>()
    val eventChangeColor : LiveData<Int>
        get() = _eventChangeColor

    private val _eventChangeColorRed = MutableLiveData<Boolean>()
    val eventChangeColorRed : LiveData<Boolean>
        get() = _eventChangeColorRed

    private val _safeArgs = MutableLiveData<Int>()
    val safeArgs : LiveData<Int>
        get() = _safeArgs

    private val _toastPleaseSelect = MutableLiveData<Boolean>()
    val toastPleaseSelect : LiveData<Boolean>
        get() = _toastPleaseSelect

    private val _onStopButton = MutableLiveData<Boolean>()
    val onStopButton : LiveData<Boolean>
        get() = _onStopButton


    //===== Xml =====//
    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData : LiveData<String>
        get() = _nameLiveData

    private val _ageLiveData = MutableLiveData<String>()
    val ageLiveData : LiveData<String>
        get() = _ageLiveData

    private val _timeStartLiveData = MutableLiveData<String>()
    val timeStartLiveData : LiveData<String>
        get() = _timeStartLiveData
    //===== Xml =====//

    private var selected :Int = 0

    var C1 : DataHistory?  = null
    var C2 : DataHistory?  = null
    var C3 : DataHistory?  = null
    var C4 : DataHistory?  = null
    var C5 : DataHistory?  = null
    var C6 : DataHistory?  = null


    init{
        uiScope.launch {
            C1 = getComSelected(1)
            C2 = getComSelected(2)
            C3 = getComSelected(3)
            C4 = getComSelected(4)
            C5 = getComSelected(5)
            C6 = getComSelected(6)
        }
        _eventChangeColorRed.value = true
        _eventChangeColor.value =  0
        _eventClickSubmit.value = false
        _onStopButton.value = false
    }

    fun onStop(){
        uiScope.launch{
            clearDB()
        }
//        _onStopButton.value = true
    }


    fun selectedClick(com : Int){

        uiScope.launch {
            if(com != 0){
                Log.i("DB","${getComSelected(com)}")
                var data = getComSelected(com)

                _nameLiveData.value = data?.name
                _ageLiveData.value = data?.age
                _timeStartLiveData.value = data?.time_start
            }
        }

        _eventChangeColor.value = com
        _safeArgs.value = com
        selected = com

    }


    fun onClick(){
        if(selected != 0){
            _eventClickSubmit.value = true
        }else{
            _toastPleaseSelect.value = true
        }
    }


    //===== Suspend Function =====//
    private suspend fun clearDB(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    private suspend fun getAllDB(){
        withContext(Dispatchers.IO){
            database.getHistory()
        }
    }

    private suspend fun getComSelected(com : Int): DataHistory?{
        return withContext(Dispatchers.IO){
            var data =  database.getComSelected(com)
            if(data?.time_end != ""){
                data = null
            }
            data
        }
    }
    //===== Suspend Function =====//



}