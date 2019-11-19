package com.android.example.icafe.detail

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.icafe.database.*
import kotlinx.coroutines.*
import java.time.LocalTime

class DetailViewModel(val database: DataHistoryDatabaseDao,
                      application: Application) : AndroidViewModel(application) {


    //==== Database ====//
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //==== Database ====//



    private val _eventSubmitData = MutableLiveData<Boolean>()
    val  eventSubmitData : LiveData<Boolean>
        get() = _eventSubmitData

    private val _eventGetInput = MutableLiveData<Boolean>()
    val  eventGetInput : LiveData<Boolean>
        get() = _eventGetInput

    val _toastEmptyInput= MutableLiveData<Boolean>()
    val toastEmptyInput  : LiveData<Boolean>
        get() = _toastEmptyInput

    private val _dataIdHitden = MutableLiveData<Long>()
    val  dataIdHitden : LiveData<Long>
        get() = _dataIdHitden


    private val _dataSelected = MutableLiveData<Int>()
    val  dataSelected : LiveData<Int>
        get() = _dataSelected

    private val _init = MutableLiveData<Int>()
    val  init : LiveData<Int>
        get() = _init


    fun constructor(comSelected: Int){
        _dataSelected.value = comSelected
    }


    init {
        _eventSubmitData.value = false
        _dataIdHitden.value = -1

    }


    fun submitEnter(){
        _eventGetInput.value = true
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun checkInput(nameInput:String, ageInput:String , comSelected : Int){
        uiScope.launch {
        if(nameInput == ""  || ageInput == ""){
            _toastEmptyInput.value = true
        }else{

            var historyData = DataHistory(
                name = nameInput,
                age = ageInput,
                com = comSelected,
                time_start = LocalTime.now().toString(),
                time_end = "")

            Log.i("DB","${historyData}")
            insertData(historyData)
            var newData = getOnlyOnce()
            Log.i("historyData","${newData}")
            Log.i("historyData","------------------")

            _eventSubmitData.value = true
        }

        }
    }

    private suspend fun insertData(data : DataHistory){
        withContext(Dispatchers.IO){
            database.insert(data)
        }
    }


    private suspend fun getOnlyOnce(): DataHistory?{
        return withContext(Dispatchers.IO){
            var data = database.getHistoryLastest()
            data
        }
    }








}