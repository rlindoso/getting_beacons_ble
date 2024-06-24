package com.rlindoso.gettingbeacons.service.listener

import com.rlindoso.gettingbeacons.service.PostLogModel

interface APIListener {

    fun onSuccess(model: PostLogModel)

    fun onFailure(str: String)

}
