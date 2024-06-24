package com.rlindoso.gettingbeacons.service.repository.remote

import com.rlindoso.gettingbeacons.service.PostLogBeacon
import com.rlindoso.gettingbeacons.service.PostLogModel
import com.rlindoso.gettingbeacons.service.PostLogSend
import com.rlindoso.gettingbeacons.service.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogRemoteRepository(ipBackend: String) {

    private val mRemote = RetrofitClient.createService(LogService::class.java, ipBackend)

    fun log(logBeacons: ArrayList<PostLogBeacon>, listener: APIListener) {
        var postLogSend = PostLogSend()
        postLogSend.logBeacons = logBeacons

        val call = mRemote.log(postLogSend)
        call.enqueue(object : Callback<PostLogModel> {
            override fun onResponse(call: Call<PostLogModel>, response: Response<PostLogModel>) {
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<PostLogModel>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })
    }

}