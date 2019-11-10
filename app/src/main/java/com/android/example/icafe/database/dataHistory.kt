package com.android.example.icafe.database

import android.os.CountDownTimer
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "data_history_table")
data class dataHistory(
    @PrimaryKey(autoGenerate = true)
    var historyId: Long = 0L,

    @ColumnInfo(name = "name")
    var name :String = "",

    @ColumnInfo(name = "time")
    var time : Int = 0,

    @ColumnInfo(name = "cost")
    var cost : Int = 0
)
