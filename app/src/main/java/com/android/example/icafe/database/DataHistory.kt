package com.android.example.icafe.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.LocalTime


@Entity(tableName = "data_history_table")
data class DataHistory(

    @PrimaryKey(autoGenerate = true)
    var historyId: Long = 0L,

    @ColumnInfo(name = "name")
    var name :String = "",

    @ColumnInfo(name = "age")
    var age : String = "",

    @ColumnInfo(name = "time_start")
    var time_start : String = "",

    @ColumnInfo(name = "time_end")
    var time_end : String = "" ,

    @ColumnInfo(name = "cost")
    var cost : Int = 0,

    @ColumnInfo(name = "com")
    var com : Int = 0

)

