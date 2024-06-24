package com.rlindoso.gettingbeacons.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rlindoso.gettingbeacons.service.model.LogBeaconModel
import com.rlindoso.gettingbeacons.view.viewholder.LogViewHolder
import com.rlindoso.gettingbeacons.R

class LogAdapter : RecyclerView.Adapter<LogViewHolder>() {

    private var mLogBeaconList: List<LogBeaconModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_log, parent, false)
        return LogViewHolder(item)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(mLogBeaconList[position])
    }

    override fun getItemCount(): Int {
        return mLogBeaconList.count()
    }

    fun updateLogs(list: List<LogBeaconModel>) {
        mLogBeaconList = list
        notifyDataSetChanged()
    }
}