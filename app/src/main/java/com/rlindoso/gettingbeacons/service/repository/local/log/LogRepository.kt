package com.rlindoso.gettingbeacons.service.repository.local.log

import android.content.Context
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel
import com.rlindoso.gettingbeacons.service.repository.local.LogDataBase

class LogRepository (context: Context) {

    private var mDataBase = LogDataBase.getDatabase(context).logDAO()

    // CRUD - Create, Read, Update, Delete

    fun getAll(): List<LogBeaconModel> {
       return mDataBase.getLimited()
    }

    fun get(id: Int): LogBeaconModel? {
        return mDataBase.get(id)
    }

    fun getByMac(mac: String) : LogBeaconModel? {
        return mDataBase.getByMac(mac)
    }

    fun save(log: LogBeaconModel): Boolean {
        return mDataBase.save(log) > 0
    }

    fun update(log: LogBeaconModel): Boolean {
        return mDataBase.update(log) > 0
    }
}