package com.android.example.icafe.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataHistoryDatabaseDao {

        @Insert
        fun insert(dataHistory: DataHistory)

        @Update
        fun update(dataHistory: DataHistory)

        @Query("SELECT * from data_history_table WHERE historyId = :key")
        fun get(key: Long): DataHistory?

        @Query("DELETE FROM data_history_table")
        fun clear()

        @Query("SELECT * FROM data_history_table ORDER BY historyId DESC LIMIT 1")
        fun getHistoryLastest(): DataHistory?

        @Query("SELECT * FROM data_history_table ORDER BY historyId DESC")
        fun getHistory(): LiveData<List<DataHistory>>



        @Query("SELECT * from data_history_table WHERE com = :selected ORDER BY time_start DESC LIMIT 1" )
        fun getComSelected(selected:Int): DataHistory?

        @Query("SELECT * from data_history_table GROUP BY com")
        fun getEmptyGroupBy(): DataHistory


    }
