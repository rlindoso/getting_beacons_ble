package com.rlindoso.gettingbeacons.service

import com.google.gson.annotations.SerializedName

class PostLogBeacon {
    @SerializedName("macBeacon")
    var macBeacon: String = ""

    @SerializedName("distance")
    var distance: Double = 0.0

    @SerializedName("rssi")
    var rssi: Double = 0.0

    @SerializedName("txPower")
    var txPower: Double = 0.0
}

class PostLogSend {
    @SerializedName("logBeacons")
    var logBeacons: ArrayList<PostLogBeacon> = ArrayList()
}

class PostLogModel