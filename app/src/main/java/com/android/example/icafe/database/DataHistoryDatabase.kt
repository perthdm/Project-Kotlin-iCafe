package com.android.example.icafe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataHistory::class], version = 1, exportSchema = false)
abstract class DataHistoryDatabase : RoomDatabase()  {
    abstract val dataHistoryDatabaseDao: DataHistoryDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: DataHistoryDatabase? = null

        fun getInstance(context: Context): DataHistoryDatabase {
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataHistoryDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}