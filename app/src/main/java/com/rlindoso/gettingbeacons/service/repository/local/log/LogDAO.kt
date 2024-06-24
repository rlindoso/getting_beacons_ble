package com.rlindoso.gettingbeacons.service.repository.local.log

import androidx.room.*
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel

@Dao
interface LogDAO {

    @Insert
    fun save(log: LogBeaconModel) : Long

    @Update
    fun update(log: LogBeaconModel) : Int

    @Delete
    fun delete(log: LogBeaconModel) : Int

    @Query("SELECT * FROM Log WHERE id = :id")
    fun get(id: Int) : LogBeaconModel

    @Query("SELECT * FROM Log WHERE mac_beacon = :mac")
    fun getByMac(mac: String) : LogBeaconModel?

    @Query("SELECT * FROM Log ORDER BY id DESC LIMIT 100")
    fun getLimited() : List<LogBeaconModel>

}