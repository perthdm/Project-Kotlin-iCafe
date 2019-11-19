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

    private val _selectComLiveData = MutableLiveData<String>()
    val selectComLiveData : LiveData<String>
        get() = _selectComLiveData
    //===== Xml =====//

    private var selected :Int = 0


    private val _C1 = MutableLiveData<DataHistory?>()
    val C1 : LiveData<DataHistory?>
        get() = _C1

    private val _C2 = MutableLiveData<DataHistory?>()
    val C2 : LiveData<DataHistory?>
        get() = _C2

    private val _C3 = MutableLiveData<DataHistory?>()
    val C3 : LiveData<DataHistory?>
        get() = _C3

    private val _C4 = MutableLiveData<DataHistory?>()
    val C4 : LiveData<DataHistory?>
        get() = _C4

    private val _C5 = MutableLiveData<DataHistory?>()
    val C5 : LiveData<DataHistory?>
        get() = _C5

    private val _C6 = MutableLiveData<DataHistory?>()
    val C6 : LiveData<DataHistory?>
        get() = _C6


    init{

        _eventChangeColorRed.value = true
        _eventChangeColor.value =  0
        _eventClickSubmit.value = false
        _onStopButton.value = false
        _selectComLiveData.value = "-"
        getDataButton()
    }


    fun getDataButton(){
        uiScope.launch {
            _C1.value = getComSelected(1)
            _C2.value = getComSelected(2)
            _C3.value = getComSelected(3)
            _C4.value = getComSelected(4)
            _C5.value = getComSelected(5)
            _C6.value = getComSelected(6)
            Log.i("getDataButton" , "${C1.value} ${C2.value}  ${C3.value}  ${C4.value}  ${C5.value} ${C6.value}")
        }
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
                _selectComLiveData.value =  com.toString()
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