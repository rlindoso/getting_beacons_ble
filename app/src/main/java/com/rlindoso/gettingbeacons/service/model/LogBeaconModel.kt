package com.rlindoso.gettingbeacons.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Log")
class LogBeaconModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "mac_beacon")
    var macBeacon: String = ""

    @ColumnInfo(name = "distance")
    var distance: Double = 0.0

    @ColumnInfo(name = "created_at")
    var createdAt: String = Date().toString()
}