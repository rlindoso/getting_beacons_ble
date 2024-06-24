package com.rlindoso.gettingbeacons.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel
import com.rlindoso.gettingbeacons.service.repository.local.log.LogDAO

@Database(entities = [LogBeaconModel::class], version = 1)
abstract class LogDataBase : RoomDatabase() {

    abstract fun logDAO(): LogDAO

    companion object {

        private lateinit var INSTANCE: LogDataBase
        fun getDatabase(context: Context): LogDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(LogDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, LogDataBase::class.java, "logDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}