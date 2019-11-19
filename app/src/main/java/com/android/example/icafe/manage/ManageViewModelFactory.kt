package com.android.example.icafe.manage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.icafe.database.DataHistoryDatabaseDao


    class ManageViewModelFactory (
        private val dataSource: DataHistoryDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ManageViewModel::class.java)) {
                return ManageViewModel(dataSource,application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }