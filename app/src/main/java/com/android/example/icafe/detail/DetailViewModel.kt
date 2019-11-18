package com.android.example.icafe.detail

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.icafe.database.DataHistory
import com.android.example.icafe.database.DataHistoryDatabaseDao
import com.android.example.icafe.database.ObjectDataHistory
import kotlinx.coroutines.*
import java.time.LocalTime
import java.util.*
import kotlin.math.log

class DetailViewModel(val database: DataHistoryDatabaseDao, application: Application) : AndroidViewModel(application) {


    //==== Database ====//
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //==== Database ====//


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

    private val _dataIdHitden = MutableLiveData<Long>()
    val  dataIdHitden : LiveData<Long>
        get() = _dataIdHitden




    init {
        _eventInit.value = true
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
                age = ageInput.toInt(),
                com = comSelected,
                time_start = LocalTime.now().toString(),
                time_end = LocalTime.now().toString())
                insertData(historyData)
                var newData = getOnlyOnce()
            Log.i("historyData","${newData}")
            if (newData != null) {
                _dataIdHitden.value = newData.historyId
            }

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