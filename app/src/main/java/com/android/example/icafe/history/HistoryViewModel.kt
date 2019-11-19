package com.android.example.icafe.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.example.icafe.database.DataHistoryDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HistoryViewModel(val database: DataHistoryDatabaseDao, application: Application) : AndroidViewModel(application)   {

    val score = database.getHistory()

    //==== Database ====//
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //==== Database ====//



}