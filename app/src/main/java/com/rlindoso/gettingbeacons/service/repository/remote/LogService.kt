package com.rlindoso.gettingbeacons.service.repository.remote

import com.rlindoso.gettingbeacons.service.PostLogModel
import com.rlindoso.gettingbeacons.service.PostLogSend
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogService {

    @POST("log")
    fun log(
        @Body postLogSend: PostLogSend
    ): Call<PostLogModel>

}