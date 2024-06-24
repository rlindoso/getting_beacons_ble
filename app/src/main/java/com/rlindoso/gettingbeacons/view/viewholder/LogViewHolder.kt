package com.rlindoso.gettingbeacons.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel
import com.rlindoso.gettingbeacons.R

class LogViewHolder(itemView: View/*, private val listener: LogListener*/): RecyclerView.ViewHolder(itemView) {

    fun bind(logBeacon: LogBeaconModel) {
        val macBeacon = itemView.findViewById<TextView>(R.id.mac_beacon)
        val distance = itemView.findViewById<TextView>(R.id.distance)

        macBeacon.text = logBeacon.macBeacon
        distance.text = logBeacon.distance.toString()

        /* textName.setOnClickListener {
            listener.onClick(device.id)
        } */
    }

}