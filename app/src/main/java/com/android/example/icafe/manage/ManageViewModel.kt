package com.android.example.icafe.manage

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.icafe.database.DataHistory
import com.android.example.icafe.database.DataHistoryDatabaseDao
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class ManageViewModel(val database: DataHistoryDatabaseDao, application: Application): AndroidViewModel(application) {

    //==== Database ====//
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //==== Database ====//

    //===== Xml LiveData =====//
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

    //===== Xml LiveData =====//

    //===== Set -> C ====//
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
    //===== Set -> C ====//


    private val _eventClickSubmit = MutableLiveData<Boolean>()
    val eventClickSubmit : LiveData<Boolean>
        get() = _eventClickSubmit

    private val _safeArgsComSelected = MutableLiveData<Int>()
    val safeArgsComSelected : LiveData<Int>
        get() = _safeArgsComSelected

    private val _toastPleaseSelect = MutableLiveData<Boolean>()
    val toastPleaseSelect : LiveData<Boolean>
        get() = _toastPleaseSelect

    private val _onStopButton = MutableLiveData<Boolean>()
    val onStopButton : LiveData<Boolean>
        get() = _onStopButton


    private var data =  MutableLiveData<DataHistory?>()
    private var selected :Int = 0
    var cost :Int? = 0


    init{
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
            _nameLiveData.value = data.value?.name
            _ageLiveData.value =  data.value?.age
            _timeStartLiveData.value =  data.value?.time_start
        }
    }

    fun onStop(){
        uiScope.launch{
            val oldData = data.value ?:return@launch
            var timeStart : LocalTime = LocalTime.parse(data.value?.time_start)
                timeStart = LocalTime.of(timeStart.hour,timeStart.minute,timeStart.second)
//            var timeStart : LocalTime = LocalTime.parse(data?.value!!.time_start)
            var timeEnd : LocalTime =  LocalTime.of(LocalTime.now().hour,LocalTime.now().minute , LocalTime.now().second)
            var count = ChronoUnit.MINUTES.between(timeStart,timeEnd)

            oldData.time_end = timeEnd.toString()
            oldData.cost = count.toInt()*5
            cost = count.toInt()*5
            updateDB(oldData)

            data.value = null

            Log.i("Selected", selected.toString())
            when(selected){
                1 -> _C1.value = null
                2 -> _C2.value = null
                3 -> _C3.value = null
                4 -> _C4.value = null
                5 -> _C5.value = null
                6 -> _C6.value = null
            }
            _onStopButton.value = true
            getDataButton()
        }
    }

    fun selectedClick(com : Int){
        uiScope.launch {
            if(com != 0){
                Log.i("Click","${com}")
                Log.i("Click Selected","${getComSelected(com)}")
                data.value  = getComSelected(com)

                _nameLiveData.value = data.value?.name
                _ageLiveData.value =  data.value?.age
                _timeStartLiveData.value = data.value?.time_start

                if( data.value != null){
                    var timeStartShow :LocalTime = LocalTime.parse(data.value?.time_start)
                    _timeStartLiveData.value = LocalTime.of(timeStartShow.hour,timeStartShow.minute,timeStartShow.second).toString()
                }

                _selectComLiveData.value =  com.toString()
                _safeArgsComSelected.value = com
                selected = com
            }
        }
    }

    fun onClick(){
        if(selected != 0){
            _eventClickSubmit.value = true
        }else{
            _toastPleaseSelect.value = true
        }
    }


    //===== Suspend Function =====//

    private suspend fun updateDB(data: DataHistory){
        withContext(Dispatchers.IO){
            database.update(data)
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