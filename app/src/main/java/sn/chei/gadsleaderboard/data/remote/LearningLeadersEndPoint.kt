package sn.chei.gadsleaderboard.data.remote

import retrofit2.Call
import retrofit2.http.GET
import sn.chei.gadsleaderboard.data.LearningLeader


interface LearningLeadersEndPoint {

    @GET(ApiUrl.LEARNING_LEADER)
    fun getLearningLeaders(): Call<List<LearningLeader>>
}