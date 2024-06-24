package com.rlindoso.gettingbeacons.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rlindoso.gettingbeacons.service.PostLogBeacon
import com.rlindoso.gettingbeacons.service.PostLogModel
import com.rlindoso.gettingbeacons.service.constants.DeviceConstants.SHARED.IP_BACKEND
import com.rlindoso.gettingbeacons.service.listener.APIListener
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel
import com.rlindoso.gettingbeacons.service.repository.local.SecurityPreferences
import com.rlindoso.gettingbeacons.service.repository.local.log.LogRepository
import com.rlindoso.gettingbeacons.service.repository.remote.LogRemoteRepository
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.MonitorNotifier
import org.altbeacon.beacon.Region
import java.util.Date

class LogsViewModel(application: Application) : AndroidViewModel(application) {

    private val mLogRepository = LogRepository(application.applicationContext)
    private val mSharedPreferences = SecurityPreferences(application.applicationContext)
    private val mLogRemoteRepository = LogRemoteRepository(mSharedPreferences.get(IP_BACKEND))
    private val mApplication: Application = application

    private val mIpBackend = MutableLiveData<String>()
    val ipBackend: LiveData<String> = mIpBackend

    private val mLogList = MutableLiveData<List<LogBeaconModel>>()
    val logList: LiveData<List<LogBeaconModel>> = mLogList

    private val beaconManager =
        BeaconManager.getInstanceForApplication(application.applicationContext)
    private lateinit var region: Region

    private val centralMonitoringObserver = Observer<Int> { state ->
        if (state == MonitorNotifier.OUTSIDE) {
            Log.d(TAG, "outside beacon region: $region")
        } else {
            Log.d(TAG, "inside beacon region: $region")
            //sendNotification()
        }
    }

    private val centralRangingObserver = Observer<Collection<Beacon>> { beacons ->
        Log.d(TAG, "Ranged: ${beacons.count()} beacons")
        val list: ArrayList<PostLogBeacon> = ArrayList()
        for (beacon: Beacon in beacons) {
            Log.d(TAG, "$beacon about ${beacon.distance} meters away")
            val log = PostLogBeacon()
            log.distance = beacon.distance
            log.macBeacon = beacon.bluetoothAddress
            log.rssi = beacon.rssi * 1.0
            log.txPower = beacon.txPower * 1.0
            list.add(log)


            val storedBeacon = mLogRepository.getByMac(beacon.bluetoothAddress)

            if (storedBeacon != null) {
                storedBeacon.distance = beacon.distance
                mLogRepository.update(storedBeacon)
            } else {
                val logModel = LogBeaconModel()
                logModel.macBeacon = beacon.bluetoothAddress
                logModel.distance = beacon.distance
                logModel.createdAt = Date().toString()
                mLogRepository.save(logModel)
            }
        }

        load()

       if (list.size > 0) {
            remoteLogBeacons(list)
       }
    }

    private fun remoteLogBeacons(
        list: ArrayList<PostLogBeacon>
    ) {
        mLogRemoteRepository.log(list, object : APIListener {
            override fun onSuccess(log: PostLogModel) {
                // Do something
            }

            override fun onFailure(str: String) {
                // Do something
            }

        })
    }

    init {
        mIpBackend.value = mSharedPreferences.get(IP_BACKEND)

//        val list: ArrayList<PostLogBeacon> = ArrayList()
//        remoteLogBeacons(list)

        setupBeaconsListeners()
    }

    private fun setupBeaconsListeners() {
        val interval = 5 * 1000
        beaconManager.beaconParsers.clear()
        beaconManager.beaconParsers.add(
            BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")
        )

        //BeaconManager.setDebug(true)
        Beacon.setHardwareEqualityEnforced(true)

        beaconManager.setEnableScheduledScanJobs(false)
        beaconManager.backgroundBetweenScanPeriod = 0
        beaconManager.backgroundScanPeriod = interval.toLong()
        beaconManager.foregroundBetweenScanPeriod = 0
        beaconManager.foregroundScanPeriod = interval.toLong()

        region = Region("all-beacons", null, null, null)

        beaconManager.startMonitoring(region)
        beaconManager.startRangingBeacons(region)

        // These two lines set up a Live Data observer so this Activity can get beacon data from the 444Application class
        val regionViewModel =
            BeaconManager.getInstanceForApplication(mApplication).getRegionViewModel(region)
        // observer will be called each time the monitored regionState changes (inside vs. outside region)
        regionViewModel.regionState.observeForever(centralMonitoringObserver)
        // observer will be called each time a new list of beacons is ranged (typically ~1 second in the foreground)
        regionViewModel.rangedBeacons.observeForever(centralRangingObserver)
    }

    companion object {
        const val TAG = "BeaconReference"
    }

    fun load() {
        mLogList.value = mLogRepository.getAll()
    }

    fun putIpBackend(ip: String) {
        mSharedPreferences.store(IP_BACKEND, ip)
    }
}